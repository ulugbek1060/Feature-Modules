package uz.bigboys.catalog.domain.entities

import uz.bigboys.catalog.domain.entities.Price

data class Product(
    val id: Long,
    val name: String,
    val shortDetails: String,
    val details: String,
    val category: String,
    val price: Price,
    val priceWithDiscount: Price?,
    val photo: String,
)