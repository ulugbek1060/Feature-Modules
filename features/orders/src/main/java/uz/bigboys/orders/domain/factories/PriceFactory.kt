package uz.bigboys.orders.domain.factories

import uz.bigboys.orders.domain.entities.Price

interface PriceFactory {

    /**
     * Create a zero price.
     */
    val zero: Price
}