package com.example.orders.domain

import com.example.orders.domain.entities.Cart
import com.example.orders.domain.entities.Field
import com.example.orders.domain.entities.Recipient
import com.example.orders.domain.exceptions.EmptyFieldException
import com.example.orders.domain.repositories.CartRepository
import com.example.orders.domain.repositories.OrdersRepository
import com.example.orders.domain.repositories.ProductsRepository
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository,
    private val cartRepository: CartRepository,
    private val productsRepository: ProductsRepository,
) {

    /**
     * Create a new order.
     * @throws EmptyFieldException
     */
    suspend fun createOrder(cart: Cart, recipient: Recipient) {
        if (recipient.firstName.isBlank()) throw EmptyFieldException(Field.FIRST_NAME)
        if (recipient.lastname.isBlank()) throw EmptyFieldException(Field.LAST_NAME)
        if (recipient.address.isBlank()) throw EmptyFieldException(Field.ADDRESS)

        ordersRepository.makeOrder(cart, recipient)
        cartRepository.cleanUpCart()
        cart.cartItems.forEach { cartItem ->
            productsRepository.changeQuantityBy(cartItem.productId, -cartItem.quantity)
        }
    }
}