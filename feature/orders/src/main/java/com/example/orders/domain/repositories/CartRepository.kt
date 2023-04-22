package com.example.orders.domain.repositories

import com.example.orders.domain.entities.Cart

interface CartRepository {

    suspend fun getCart(): Cart

    /**
     * Remove all items from the cart.
     */
    suspend fun cleanUpCart()
}