package ru.m4bank.directposapi.cashbox.result

/**
 * Data of the current state of the cash box
 * @property hasNotPrinted - Sign of an under printed document
 * @property cycleIsOpen - Sign of an open shift
 * @property lastFd - Last document number
 * */
data class CashBoxStatusResult(
    val hasNotPrinted: Boolean,
    val cycleIsOpen: Boolean,
    val lastFd: Int,
)
