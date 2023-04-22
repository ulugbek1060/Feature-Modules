package uz.bigboys.multomodulewithfeatures.glue.orders.repositories

import com.example.orders.domain.entities.Cart
import com.example.orders.domain.entities.Order
import com.example.orders.domain.entities.OrderStatus
import com.example.orders.domain.entities.Recipient
import com.example.orders.domain.repositories.OrdersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.bigboys.common.Container
import uz.bigboys.data.OrdersDataRepository
import uz.bigboys.data.orders.entity.CreateOrderDataEntity
import uz.bigboys.data.orders.entity.CreateOrderItemDataEntity
import uz.bigboys.multomodulewithfeatures.glue.orders.entity.OrderUsdPrice
import uz.bigboys.multomodulewithfeatures.glue.orders.mapper.OrderMapper
import uz.bigboys.multomodulewithfeatures.glue.orders.mapper.OrderStatusMapper
import uz.bigboys.multomodulewithfeatures.glue.orders.mapper.RecipientMapper
import javax.inject.Inject

class AdapterOrdersRepository @Inject constructor(
    private val ordersDataRepository: OrdersDataRepository,
    private val orderStatusMapper: OrderStatusMapper,
    private val orderMapper: OrderMapper,
    private val recipientMapper: RecipientMapper,
) : OrdersRepository {

    override suspend fun makeOrder(cart: Cart, recipient: Recipient) {
        ordersDataRepository.createOrder(CreateOrderDataEntity(
            recipientDataEntity = recipientMapper.toRecipientDataEntity(recipient),
            items = cart.cartItems.map {
                CreateOrderItemDataEntity(
                    productId = it.productId,
                    quantity = it.quantity,
                    priceUsdCents = (it.price as OrderUsdPrice).usdCents
                )
            }
        ))
    }

    override suspend fun changeStatus(orderUUid: String, orderStatus: OrderStatus) {
        ordersDataRepository.changeStatus(
            orderUUid,
            orderStatusMapper.toOrderStatusDataValue(orderStatus)
        )
    }

    override suspend fun getOrders(): Flow<Container<List<Order>>> {
        return ordersDataRepository.getOrders().map { container ->
            container.map { list ->
                list.map { orderDataEntity ->
                    orderMapper.toOrder(orderDataEntity)
                }
            }
        }
    }

    override fun reload() {
        ordersDataRepository.reload()
    }
}