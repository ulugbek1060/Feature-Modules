package com.example.orders.domain.repositories

interface ProductsRepository {

    /**
     * Change quantity of the specified product.
     */
    suspend fun changeQuantity(productId: Long, byValue: Int)

}