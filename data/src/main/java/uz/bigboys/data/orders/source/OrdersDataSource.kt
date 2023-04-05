package uz.bigboys.data.orders.source

import uz.bigboys.data.orders.entity.CreateOrderDataEntity
import uz.bigboys.data.orders.entity.OrderDataEntity
import uz.bigboys.data.orders.entity.OrderStatusDataValue

interface OrdersDataSource {

   suspend fun getOrders(): List<OrderDataEntity>

   suspend fun setStatus(uuid: String, newStatus: OrderStatusDataValue)

   suspend fun createOrder(createOrderDataEntity: CreateOrderDataEntity)

}