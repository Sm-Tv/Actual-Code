package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class FiscalFractionData(
    @SerializedName("denominator") val denominator: Int,
    @SerializedName("nominator") val nominator: Int,
)
