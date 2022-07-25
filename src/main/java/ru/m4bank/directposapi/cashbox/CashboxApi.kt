package ru.m4bank.directposapi.cashbox

import ru.m4bank.directposapi.cashbox.data.cart.RawLabel
import ru.m4bank.directposapi.cashbox.data.cart.ShoppingCart
import ru.m4bank.directposapi.cashbox.data.enum.AttrCalculation
import ru.m4bank.directposapi.cashbox.data.enum.TaxationSystem
import ru.m4bank.directposapi.cashbox.result.CashBoxStatusResult
import ru.m4bank.directposapi.cashbox.result.ServerRequestResult

interface CashboxApi {

    /**
     * Prints a fiscal receipt
     * @param isCardPayment = true - card payment
     * @param isCardPayment = false - cash payment
     * @param shoppingCart - shopping cart
     * @param cashierName - cashier's name
     * @param merchantName - merchant name
     * @param taxationSystem - sign of calculation
     * @param taxationSystem - applicable taxation system
     * @param exponent - number of digits after the decimal point in currency
     * @param print = true - print fiscal document
     * @param print = false - do not print fiscal document
     * */
    suspend fun printReceipt(
        isCardPayment: Boolean,
        shoppingCart: ShoppingCart,
        cashierName: String,
        merchantName: String?,
        attrCalculation: AttrCalculation,
        taxationSystem: TaxationSystem,
        exponent: Int,
        print: Boolean = true,
    )

    /**
     * Prints a fiscal correction receipt
     * @param print = true - print fiscal document
     * @param print = false - do not print fiscal document
     * @param correction - correction fiscal receipt object
     * */
    suspend fun printCorrectionReceipt(
        correction: ServerRequestResult,
        print: Boolean = true,
    )

    /**
     * Checking the brand of the marked goods
     * @param rawLabel - string containing the scanned product brand
     * */
    suspend fun checkLabel(
        rawLabel: RawLabel,
    ): String // todo что за формат? Строка ответа приходит в cartItem в fiscalData в поле labelVerificationResult //todo на ревью нужно передавать _поправленный_ код, переписка в TODO не нужна

    /** Requests the checkout status */
    suspend fun cashBoxStatus(): CashBoxStatusResult

    /** Print the last printed fiscal document */
    suspend fun printLastFiscalDocument()

    /** Prints a document by its fiscal number */
    suspend fun printFiscalDocumentByFd(fd: Int)

    /** Prints an unprinted document */
    suspend fun printNotPrintedDocument()

    /** Closing of the fiscal shift */
    suspend fun closeCycle()

    /** Opening of the fiscal shift */
    suspend fun openCycle()

    /** Report on the current state of settlements */
    suspend fun getBillingReport(): ServerRequestResult
}
