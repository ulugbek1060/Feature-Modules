package uz.bigboys.multomodulewithfeatures.glue.orders.mapper

import uz.bigboys.orders.domain.entities.Order
import uz.bigboys.orders.domain.entities.OrderItem
import uz.bigboys.data.orders.entity.OrderDataEntity
import uz.bigboys.data.orders.entity.RecipientDataEntity
import uz.bigboys.multomodulewithfeatures.fromatter.DateTimeFormatter
import uz.bigboys.multomodulewithfeatures.fromatter.PriceFormatter
import uz.bigboys.multomodulewithfeatures.glue.orders.entity.OrderUsdPrice
import javax.inject.Inject

class OrderMapper @Inject constructor(
    private val orderStatusMapper: OrderStatusMapper,
    private val priceFormatter: PriceFormatter,
    private val dateTimeFormatter: DateTimeFormatter,
) {

    fun toOrder(orderDataEntity: OrderDataEntity): Order {
        return Order(
            uuid = orderDataEntity.uuid,
            status = orderStatusMapper.toOrderStatus(orderDataEntity.status),
            createdAt = dateTimeFormatter.formatDateTime(orderDataEntity.createdAtMillis),
            orderDeliverInfo = orderDataEntity.recipient.toDeliverInfo(),
            orderItems = orderDataEntity.items.map {
                OrderItem(
                    id = it.id,
                    name = it.productName,
                    quantity = it.quantity,
                    price = OrderUsdPrice(it.priceUsdCents, priceFormatter)
                )
            }
        )
    }

    private fun RecipientDataEntity.toDeliverInfo(): String {
        return "$firstName $lastName, $address"
    }
}