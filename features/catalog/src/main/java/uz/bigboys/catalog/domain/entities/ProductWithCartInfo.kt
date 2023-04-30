package uz.bigboys.catalog.domain.entities

import uz.bigboys.catalog.domain.entities.Product

data class ProductWithCartInfo(
    val product: Product,
    val isInCart: Boolean,
)
