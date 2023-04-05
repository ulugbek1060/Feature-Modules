package uz.bigboys.data.orders.entity

data class OrderItemDataEntity(
    val id: String,
    val productName: String,
    val quantity: Int,
    val priceUsdCents: Int,
)
