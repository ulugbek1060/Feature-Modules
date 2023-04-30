package uz.bigboys.orders.domain.repositories

import uz.bigboys.orders.domain.entities.Cart
import uz.bigboys.orders.domain.entities.Order
import uz.bigboys.orders.domain.entities.OrderStatus
import uz.bigboys.orders.domain.entities.Recipient
import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container

interface OrdersRepository {

    /**
     * Create a new order by using items in the cart.
     */
    suspend fun makeOrder(cart: Cart, recipient: Recipient)

    /**
     * Change status of the specified order.
     */
    suspend fun changeStatus(orderUuid: String, status: OrderStatus)

    /**
     * Listen for order list.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getOrders(): Flow<Container<List<Order>>>

    /**
     * Reload orders flow returned by [getOrders]
     */
    fun reload()

}