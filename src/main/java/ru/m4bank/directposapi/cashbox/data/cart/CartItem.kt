package ru.m4bank.directposapi.cashbox.data.cart

import ru.m4bank.directposapi.cashbox.data.fiscal.FiscalData

/**
 * @property total total sum of cart item in minimal units of currency
 * @property price price of cart item in minimal units of currency
 * @property currencyCode number code of currency
 * @property count count in minimal units of count (generally, 'total' == 'price' * 'count')
 * @property countExponent number of decimal digits of count ('count = 150 & countExponent = 2' means '1.5' quantity)
 * @property name associated name of cart item
 * @property catalogItemId associated identifier in a catalog
 * @property data arbitrary data associated with cart item (e.g. details or some ID)
 */
data class CartItem(
    val total: Long,
    val price: Long,
    val currencyCode: Int,
    val count: Int,
    val countExponent: Int = 0,
    val name: String? = null,
    val catalogItemId: String? = null,
    val data: String? = null,
    val fiscalData: FiscalData? = null,
)
