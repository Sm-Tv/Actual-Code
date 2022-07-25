package ru.m4bank.directposapi.cashbox.data.enum

/**
 * Expected product inspection status
 * @property PRICE_GOODS - inspection status, price goods
 * @property MEASURED_GOODS - inspection status, measured goods
 * @property RETURN_PRICE_GOODS - inspection status, return price goods
 * @property RETURN_MEASURED_GOODS - inspection status, return measured goods
 * @property STATUS_WILL_NOT_CHANGE - status does not change
 * */
enum class ProductInspectionStatus(val index: Int) {
    PRICE_GOODS(1),
    MEASURED_GOODS(2),
    RETURN_PRICE_GOODS (3),
    RETURN_MEASURED_GOODS(4),
    STATUS_WILL_NOT_CHANGE(255)
}
