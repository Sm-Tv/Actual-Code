package ru.m4bank.directposapi.cashbox.exception

//todo нужно документировать публичные методы, выбрасывающие это исключение, с помощью @Throws
open class CashboxApiException(message: String) : RuntimeException(message)

class CashboxApiExceptionValidate(message: String) : CashboxApiException(message)
