package ru.m4bank.directposapi.cashbox.result

/**
 * Data showing the result of the past operation
 * @param message - message about the success or failure of the request
 * @param result - server error code, if it is 0, then the command succeeded
 * */
data class ServerRequestResult (
    val message: String?,
    val result: Int,
)
