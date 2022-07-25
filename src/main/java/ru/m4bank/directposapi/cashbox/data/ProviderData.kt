package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class ProviderData(
    @SerializedName("name") val name: String,
    @SerializedName("phones") val phones: List<String>,
)
