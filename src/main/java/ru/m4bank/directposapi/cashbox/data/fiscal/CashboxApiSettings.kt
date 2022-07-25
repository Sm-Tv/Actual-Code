package ru.m4bank.directposapi.cashbox.data.fiscal

/**
 * Data for connecting to the server KKT
 * @param authName - authorized username
 * @param authPassword - authorized password
 * @param url - base url to connect
 * @param externalId - unique identifier for the cash box
 * */
data class CashboxApiSettings(
    val authName: String,
    val authPassword: String,
    val url: String,
    val externalId: String,
)
