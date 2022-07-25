package ru.m4bank.directposapi.cashbox

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.m4bank.directposapi.cashbox.data.*
import ru.m4bank.directposapi.cashbox.data.cart.CartItem
import ru.m4bank.directposapi.cashbox.data.cart.RawLabel
import ru.m4bank.directposapi.cashbox.data.cart.ShoppingCart
import ru.m4bank.directposapi.cashbox.data.enum.AttrCalculation
import ru.m4bank.directposapi.cashbox.data.enum.ProductInspectionStatus
import ru.m4bank.directposapi.cashbox.data.enum.TaxationSystem
import ru.m4bank.directposapi.cashbox.data.fiscal.CashboxApiSettings
import ru.m4bank.directposapi.cashbox.exception.CashboxApiException
import ru.m4bank.directposapi.cashbox.exception.CashboxApiExceptionValidate
import ru.m4bank.directposapi.cashbox.result.CashBoxStatusResult
import ru.m4bank.directposapi.cashbox.result.ServerRequestResult
import ru.m4bank.directposapi.cashbox.util.GsonUtil
import java.math.BigDecimal

internal class CashboxApiImpl(settings: CashboxApiSettings) : CashboxApi {

    private val baseUrl = settings.url.removeSuffix("/")
    private val client = HttpClient(CIO) {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = settings.authName,
                        password = settings.authPassword
                    )
                }
            }
        }
    }

    @Throws(CashboxApiException::class)
    override suspend fun printReceipt(
        isCardPayment: Boolean,
        shoppingCart: ShoppingCart,
        cashierName: String,
        merchantName: String?,
        attrCalculation: AttrCalculation,
        taxationSystem: TaxationSystem,
        exponent: Int,
        print: Boolean,
    ) {
        val receipt = getReceipt(
            isCardPayment = isCardPayment,
            shoppingCart = shoppingCart,
            cashierName = cashierName,
            merchantName = merchantName,
            attrCalculation = attrCalculation,
            taxationSystem = taxationSystem,
            exponent = exponent,
        )
        val response = post("/receipt.json", receipt, print)
        val receiptResponse = GsonUtil.fromJSON<Receipt>(response)
        validate(receiptResponse)
    }

    @Throws(CashboxApiException::class)
    override suspend fun printCorrectionReceipt(
        correction: ServerRequestResult,
        print: Boolean,
    ) {
        val response = post("/correction.json", correction, print)
        val correctionResponse = GsonUtil.fromJSON<Receipt>(response)
        validate(correctionResponse)
    }

    @Throws(CashboxApiException::class)
    override suspend fun checkLabel(
        rawLabel: RawLabel,
    ): String { // todo что возвращается?
        // TODO Возвращается строка результат ответа после проверки марки.
        // TODO Это то что нам приходит в fiscalData в параметре  labelVerificationResult
        val productInspectionStatus = ProductInspectionStatus.PRICE_GOODS.index
        val labelCheckData = LabelCheckResult(
            expectedStatus = productInspectionStatus,
            quantity = QuantityLabel(),
            rawLabel = rawLabel
        )
        val response = post("/labelcheck.json", labelCheckData, printOption = null)
        val labelCheckResponse = GsonUtil.fromJSON<Receipt>(response)
        processResponse(labelCheckResponse)
        return response
    }

    @Throws(CashboxApiException::class)
    override suspend fun cashBoxStatus(): CashBoxStatusResult {
        val response = GsonUtil.fromJSON<Receipt>(
            get(path = "/cashboxstatus.json", fdNumber = null)
        )
        validate(response)
        val cashBoxStatus = response.cashboxResult!!.let {
            CashBoxStatusResult(
                hasNotPrinted = it.hasNotPrinted,
                cycleIsOpen = it.fiscalStatus.cycleIsOpen,
                lastFd = it.fiscalStatus.currentStatus.lastFd
            )
        }
        return cashBoxStatus
    }

    @Throws(CashboxApiException::class)
    override suspend fun printLastFiscalDocument() {
        printFiscalDocumentByFd(cashBoxStatus().lastFd)
    }

    @Throws(CashboxApiException::class)
    override suspend fun printFiscalDocumentByFd(fd: Int) {
        val response = get(path = "/findfsdoc.json", fdNumber = fd)
        val findFiscalDocResponse = GsonUtil.fromJSON<Receipt>(response)
        validate(findFiscalDocResponse)
    }

    @Throws(CashboxApiException::class)
    override suspend fun printNotPrintedDocument() {
        val response = get(path = "/printnotprinted.json", fdNumber = null)
        val printNotPrintedResponse = GsonUtil.fromJSON<Receipt>(response)
        validate(printNotPrintedResponse)
    }

    @Throws(CashboxApiException::class)
    override suspend fun closeCycle() {
        val response = get(path = "/cycleclose.json", fdNumber = null)
        val cycleCloseResponse = GsonUtil.fromJSON<Receipt>(response)
        return validate(cycleCloseResponse)
    }

    @Throws(CashboxApiException::class)
    override suspend fun openCycle() {
        val response = get(path = "/cycleopen.json", fdNumber = null)
        val cycleOpenResponse = GsonUtil.fromJSON<Receipt>(response)
        return validate(cycleOpenResponse)
    }

    @Throws(CashboxApiException::class)
    override suspend fun getBillingReport(): ServerRequestResult {
        val response = get(path = "/calcreport.json", fdNumber = null)
        val calcReportResponse = GsonUtil.fromJSON<Receipt>(response)
        validate(calcReportResponse)
        return ServerRequestResult(
            message = calcReportResponse.message,
            result = calcReportResponse.result,
        )
    }

    private fun processResponse(response: Receipt): Receipt {
        validate(response)
        return response
    }

    private suspend fun get(path: String, fdNumber: Int?): String {
        val response = client.request<String> {
            parameter("fd", fdNumber)
            url(baseUrl + path)
            method = HttpMethod.Get
        }
        return response
    }

    private suspend fun post(path: String, body: Any, printOption: Boolean?): String {
        val response = client.request<String> {
            if (printOption != null) {
                val silentValue = if (printOption) 0 else 1
                parameter("silent", silentValue)
            }
            url(baseUrl + path)
            contentType(ContentType.Application.Json)
            this.body = GsonUtil.toJSON(body)
            method = HttpMethod.Post
        }
        return response
    }

    private fun validate(response: Receipt) {
        if (response.result != 0) {
            val message = response.message
            throw CashboxApiExceptionValidate(message.toString())
        }
    }

    private fun getOperationSimpleCartItem(
        cartItem: CartItem,
        exponent: Int,
    ): FiscalOperation {
        val name = cartItem.name
        val paymentType = cartItem.fiscalData?.billingMethod?.code
        val type = cartItem.fiscalData?.billingPurpose?.code
        val vatRate = cartItem.fiscalData?.vatValue?.code
        val str = cartItem.fiscalData?.labelVerificationResult
        val quantity = cartItem.count.toString()
        val price = formatAmount(cartItem.price, exponent)

        return FiscalOperation(
            name = name,
            paymentType = paymentType,
            price = price,
            labelCheckResult = if (str != null) labelCheckResult(str) else null,
            quantity = quantity,
            type = type,
            vatRate = vatRate
        )
    }

    private fun labelCheckResult(str: String): LabelCheckResult? {
        return GsonUtil.fromJSON(str)
    }

    private fun formatAmount(amount: Long, exponent: Int): String {
        return BigDecimal(amount).movePointLeft(exponent).toString()
    }

    private fun getReceipt(
        isCardPayment: Boolean,
        shoppingCart: ShoppingCart,
        cashierName: String,
        merchantName: String?,
        attrCalculation: AttrCalculation,
        taxationSystem: TaxationSystem,
        exponent: Int,
    ): Receipt {
        val fiscalOperations = shoppingCart.items.map { cartItem ->
            getOperationSimpleCartItem(cartItem, exponent)
        }
        val (unlabeled, labeled) = fiscalOperations.partition {
            it.labelCheckResult == null
        }
        val amount = shoppingCart.totalSum.amount
        val price = formatAmount(amount, exponent)
        val fiscalDocument = FiscalDocument(
            cash = if (isCardPayment) null else price,
            card = if (isCardPayment) price else null,
            operations = unlabeled,
            labeledOperations = labeled,
            cashier = cashierName,
            paymentAttr = attrCalculation.index,
            place = merchantName,
            tax = taxationSystem.index
        )
        return Receipt(fiscalDocument = fiscalDocument, result = 0)
    }
}
