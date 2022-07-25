package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class CorrectionReason(
    @SerializedName("isIndependent") val isIndependent: Int,
    @SerializedName("date") val date: String,
    @SerializedName("docNumber") val docNumber: String,
)
