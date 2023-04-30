package uz.bigboys.orders.domain

import uz.bigboys.orders.domain.entities.Order
import uz.bigboys.orders.domain.entities.OrderStatus
import uz.bigboys.orders.domain.exceptions.InvalidOrderStatusException
import uz.bigboys.orders.domain.repositories.OrdersRepository
import javax.inject.Inject

class CancelOrderUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository
) {

    /**
     * Cancel the specified order.
     * @throws [InvalidOrderStatusException]
     */
    suspend fun cancelOrder(order: Order) {
        if (!canCancel(order)) {
            throw InvalidOrderStatusException()
        }
        ordersRepository.changeStatus(order.uuid, OrderStatus.CANCELLED)
    }

     fun canCancel(order: Order): Boolean {
        return order.status == OrderStatus.CREATED
                || order.status == OrderStatus.ACCEPTED
    }
}