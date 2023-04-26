package com.example.catalog.domain.entities

data class ProductWithCartInfo(
    val product: Product,
    val isInCart: Boolean,
)
