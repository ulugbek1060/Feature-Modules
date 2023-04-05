package uz.bigboys.data.orders.entity

data class CreateOrderDataEntity(
    val items: List<CreateOrderItemDataEntity>,
    val recipientDataEntity: RecipientDataEntity,
)