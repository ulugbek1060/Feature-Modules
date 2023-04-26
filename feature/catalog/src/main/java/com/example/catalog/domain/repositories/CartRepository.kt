package com.example.catalog.domain.repositories

import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container

interface CartRepository {

    /**
     * Listen for all product IDs already added to the Cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProductIdentifiersInCart(): Flow<Container<Set<Long>>>

    /**
     * Reload the flow returned by [getProductIdentifiersInCart]
     */
    fun reload()

    /**
     * Add a new product to the cart.
     */
    suspend fun addToCart(productId: Long)

}