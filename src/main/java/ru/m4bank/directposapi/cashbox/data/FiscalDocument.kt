package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class FiscalDocument(
    @SerializedName("address") val address: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("cash") val cash: String? = null,
    @SerializedName("card") val card: String? = null,
    @SerializedName("cashier") val cashier: String,
    @SerializedName("clientId") val clientId: String? = null,
    @SerializedName("cycle") val cycle: Int? = null,
    @SerializedName("docNumber") val docNumber: Int? = null,
    @SerializedName("dt") val dt: String? = null,
    @SerializedName("externalId") val externalId: String? = null,
    @SerializedName("ffd") val ffd: String? = null,
    @SerializedName("fiscalCode") val fiscalCode: String? = null,
    @SerializedName("footer") val footer: String? = null,
    @SerializedName("fsNumber") val fsNumber: String? = null,
    @SerializedName("ftsSite") val ftsSite: String? = null,
    @SerializedName("header") val header: String? = null,
    @SerializedName("correctionReason") val correctionReason: CorrectionReason? = null,
    @SerializedName("operations") val operations: List<FiscalOperation>? = null,
    //there must be a typo, because in the json response such a field "labledOperations" will come
    @SerializedName("labledOperations") val labeledOperations: List<FiscalOperation>? = null,
    @SerializedName("ownerEmail") val ownerEmail: String? = null,
    @SerializedName("ownerInn") val ownerInn: String? = null,
    @SerializedName("ownerName") val ownerName: String? = null,
    @SerializedName("paymentAttr") val paymentAttr: Int,
    @SerializedName("place") val place: String?,
    @SerializedName("receipt") val receipt: Int? = null,
    @SerializedName("regNumber") val regNumber: String? = null,
    @SerializedName("tax") val tax: Int,
    @SerializedName("type") val type: Int? = null,
    @SerializedName("vat10") val vat10: String? = null,
    @SerializedName("vat20") val vat20: String? = null,
    @SerializedName("vat30") val vat30: String? = null,
    @SerializedName("vat40") val vat40: String? = null,
)
