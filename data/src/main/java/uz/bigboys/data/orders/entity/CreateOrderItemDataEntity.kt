package uz.bigboys.data.orders.entity

data class CreateOrderItemDataEntity(
    val productId: Long,
    val quantity: Int,
    val priceUsdCents: Int
)