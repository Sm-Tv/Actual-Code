package ru.m4bank.directposapi.cashbox.data.cart

/**
 * Common representation of shopping cart
 * @property items - List of items that make up the shopping cart
 * */
data class ShoppingCart (
    val items: List<CartItem>,
) {

    val total: Long by lazy {
        items.sumOf { it.total }
    }

    val currencyCode: Int by lazy {
        items.map { it.currencyCode }.distinct().single()
    }

    val totalSum: Money by lazy {
        Money(total, currencyCode)
    }
}
