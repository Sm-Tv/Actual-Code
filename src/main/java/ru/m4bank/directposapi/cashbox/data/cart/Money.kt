package ru.m4bank.directposapi.cashbox.data.cart

/**
 * Common representation of amount and currency
 * @property amount in minimal units of corresponding currency
 * @property currencyCode corresponding currency
 */
data class Money (
    val amount: Long,
    val currencyCode: CurrencyCode,
)
