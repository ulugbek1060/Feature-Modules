package com.example.orders.domain

import com.example.orders.domain.entities.Order
import com.example.orders.domain.repositories.OrdersRepository
import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository,
) {

    /**
     * Listen for all user's orders.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getOrders(): Flow<Container<List<Order>>> {
        return ordersRepository.getOrders()
    }

    /**
     * Reload the flow returned by [getOrders].
     */
    fun reload() {
        ordersRepository.reload()
    }

}