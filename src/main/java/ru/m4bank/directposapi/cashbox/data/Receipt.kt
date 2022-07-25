package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class Receipt(
    @SerializedName("message") val message: String? = null,
    @SerializedName("status") val cashboxResult: CashBoxStatus? = null,
    @SerializedName("document") val fiscalDocument: FiscalDocument?,
    @SerializedName("result") val result: Int,
)
