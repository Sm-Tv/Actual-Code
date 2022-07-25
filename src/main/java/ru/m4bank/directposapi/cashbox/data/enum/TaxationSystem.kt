package ru.m4bank.directposapi.cashbox.data.enum

/** Applicable taxation system
 * @property TAX_ALL - Applicable taxation system all
 * @property TAX_SIMPLIFIED_INCOME - Applicable taxation system simplified, income
 * @property TAX_SIMPLIFIED_INCOME_MINUS_EXPENSES - Applicable taxation system simplified, income minus expenses
 * @property TAX_SINGLE_ON_IMPUTED_INCOME - Applicable taxation system a single tax on imputed income
 * @property TAX_SINGLE_AGRICULTURAL - Applicable taxation system single agricultural tax
 * @property TAX_PATENT_SYSTEM - Applicable taxation system patent taxation system
 * */
enum class TaxationSystem(val index: Int) {
    TAX_ALL(1),
    TAX_SIMPLIFIED_INCOME(2),
    TAX_SIMPLIFIED_INCOME_MINUS_EXPENSES(4),
    TAX_SINGLE_ON_IMPUTED_INCOME(8),
    TAX_SINGLE_AGRICULTURAL(16),
    TAX_PATENT_SYSTEM(32),
}
