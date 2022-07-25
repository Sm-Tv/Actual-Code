package ru.m4bank.directposapi.cashbox.data

import com.google.gson.annotations.SerializedName

internal data class CashBoxStatus(
    @SerializedName("fs") val fiscalStatus: StatusFiscalAccumulator,
    @SerializedName("hasNotPrinted") val hasNotPrinted: Boolean,
)

internal data class StatusFiscalAccumulator(
    @SerializedName("status") val currentStatus: FiscalStatus,
    @SerializedName("cycleIsOpen") val cycleIsOpen: Boolean,
)

internal data class FiscalStatus(
    @SerializedName("lastFd") val lastFd: Int,
)
