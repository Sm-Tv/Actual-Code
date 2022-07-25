package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class LabelCheckResult(
    @SerializedName("actualStatus") val actualStatus: Int? = null,
    @SerializedName("clFlags") val clFlags: Int? = null,
    @SerializedName("lct2100") val lct2100: Int? = null,
    //there must be a typo, because in the json response such a field "excpectedStatus" will come
    @SerializedName("excpectedStatus") val expectedStatus: Int? = null,
    @SerializedName("checkFlags") val checkFlags: Int? = null,
    @SerializedName("fraction") val fraction: FiscalFractionData? = null,
    @SerializedName("quantity") val quantity: QuantityLabel? = null,
    @SerializedName("rawLabel") val rawLabel: String,
    @SerializedName("reqCode2105") val reqCode2105: Int? = null,
    @SerializedName("reqDt") val reqDt: String? = null,
    @SerializedName("reqResult") val reqResult: Int? = null,
    @SerializedName("st2109") val st2109: Int? = null,
)

internal data class QuantityLabel(
    @SerializedName("quantity") val quantity: Int? = 1,
    @SerializedName("rm") val rm: String? = null,
    @SerializedName("value") val value: String? = null,
)
