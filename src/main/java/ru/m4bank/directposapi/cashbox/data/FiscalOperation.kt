package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class FiscalOperation(
    @SerializedName("agentFlag") val agentFlag: Int? = null,
    @SerializedName("cost") val cost: String? = null,
    @SerializedName("costVat") val costVat: String? = null,
    @SerializedName("countryCode") val countryCode: String? = null,
    @SerializedName("declarationNumber") val declarationNumber: String? = null,
    @SerializedName("name") val name: String?,
    @SerializedName("paymentType") val paymentType: Int?,
    @SerializedName("price") val price: String?,
    @SerializedName("priceVat") val priceVat: String? = null,
    @SerializedName("labelCheckResult") val labelCheckResult: LabelCheckResult? = null,
    @SerializedName("productCode") val productCode: ProductCode? = null,
    @SerializedName("providerData") val providerData: ProviderData? = null,
    @SerializedName("providerInn") val providerInn: String? = null,
    @SerializedName("quantity") val quantity: String,
    @SerializedName("type") val type: Int?,
    @SerializedName("vatRate") val vatRate: Int?,
)
