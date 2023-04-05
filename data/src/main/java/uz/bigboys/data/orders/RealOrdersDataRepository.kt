package uz.bigboys.data.orders

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container
import uz.bigboys.common.flow.LazyFlowSubjectFactory
import uz.bigboys.data.OrdersDataRepository
import uz.bigboys.data.orders.entity.CreateOrderDataEntity
import uz.bigboys.data.orders.entity.OrderDataEntity
import uz.bigboys.data.orders.entity.OrderStatusDataValue
import uz.bigboys.data.orders.source.OrdersDataSource
import javax.inject.Inject

class RealOrdersDataRepository @Inject constructor(
   private val ordersDataSource: OrdersDataSource,
   lazyFlowSubjectFactory: LazyFlowSubjectFactory,
) : OrdersDataRepository {

   private val ordersSubject = lazyFlowSubjectFactory.create {
      delay(1000)
      ordersDataSource.getOrders()
   }

   override fun getOrders(): Flow<Container<List<OrderDataEntity>>> {
      return ordersSubject.listen()
   }

   override fun reload() {
      ordersSubject.newAsyncLoad()
   }

   override suspend fun changeStatus(orderUuid: String, status: OrderStatusDataValue) {
      ordersDataSource.setStatus(orderUuid, status)
      ordersSubject.newAsyncLoad(silently = true)
   }

   override suspend fun createOrder(data: CreateOrderDataEntity) {
      ordersDataSource.createOrder(data)
      ordersSubject.newAsyncLoad(silently = true)
   }

}