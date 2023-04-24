package com.example.orders.domain.repositories

interface ProductsRepository {

    /**
     * Change quantity of the specified product.
     */
    suspend fun changeQuantityBy(productId: Long, byValue: Int)

}