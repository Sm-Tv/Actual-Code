package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class ProductCode(
    @SerializedName("data") val data: String,
    @SerializedName("type") val type: Int,
)
