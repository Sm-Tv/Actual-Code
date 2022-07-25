package ru.m4bank.directposapi.cashbox.data.enum

/** sign of calculation
 * @property ATTR_ARRIVAL - Arrival
 * @property ATTR_PARISH_ARRIVAL - Return arrival
 * @property ATTR_EXPENSE - Expense
 * @property ATTR_EXPENSE_REFUND - Return expense
 * */
enum class AttrCalculation(val index: Int) {
    ATTR_ARRIVAL(1),
    ATTR_PARISH_ARRIVAL(2),
    ATTR_EXPENSE(3),
    ATTR_EXPENSE_REFUND(4),
}
