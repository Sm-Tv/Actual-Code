package ru.m4bank.directposapi.cashbox

import ru.m4bank.directposapi.cashbox.data.fiscal.CashboxApiSettings

object CashboxApiFactory {
    fun initCashboxApi(settings: CashboxApiSettings): CashboxApi {
        return CashboxApiImpl(settings)
    }
}
