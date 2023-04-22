package com.example.orders.domain.repositories

import com.example.orders.domain.entities.Cart
import com.example.orders.domain.entities.Order
import com.example.orders.domain.entities.OrderStatus
import com.example.orders.domain.entities.Recipient
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
    suspend fun changeStatus(orderUUid: String, orderStatus: OrderStatus)

    /**
     * Listen for order list.
     * @returns infinite flow, always success; errors are delivered to [Container]
     */
    suspend fun getOrders(): Flow<Container<List<Order>>>

    /**
     * Reload orders flow returned by [getOrders]
     */
    fun reload()
}