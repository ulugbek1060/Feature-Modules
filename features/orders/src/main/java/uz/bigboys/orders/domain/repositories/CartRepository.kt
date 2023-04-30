package uz.bigboys.orders.domain.repositories

import uz.bigboys.orders.domain.entities.Cart

interface CartRepository {

    suspend fun getCart(): Cart

    /**
     * Remove all items from the cart.
     */
    suspend fun cleanUpCart()
}