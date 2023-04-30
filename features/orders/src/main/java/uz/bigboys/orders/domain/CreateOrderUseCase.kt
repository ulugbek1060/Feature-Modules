package uz.bigboys.orders.domain

import uz.bigboys.orders.domain.entities.Cart
import uz.bigboys.orders.domain.entities.Field
import uz.bigboys.orders.domain.entities.Recipient
import uz.bigboys.orders.domain.exceptions.EmptyFieldException
import uz.bigboys.orders.domain.repositories.CartRepository
import uz.bigboys.orders.domain.repositories.OrdersRepository
import uz.bigboys.orders.domain.repositories.ProductsRepository
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