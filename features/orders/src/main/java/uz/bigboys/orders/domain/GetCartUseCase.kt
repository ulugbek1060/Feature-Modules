package uz.bigboys.orders.domain

import uz.bigboys.orders.domain.entities.Cart
import uz.bigboys.orders.domain.repositories.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    /**
     * Get the current user's cart.
     */
    suspend fun getCart(): Cart {
        return cartRepository.getCart()
    }

}