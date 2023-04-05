package uz.bigboys.data.cart.source

import uz.bigboys.data.cart.entity.CartItemDataEntity

interface CartDataSource {

   suspend fun clearCart()

   suspend fun getCart(): List<CartItemDataEntity>

   suspend fun saveToCart(productId: Long, quantity: Int)

   suspend fun delete(cartItemId: Long)

   suspend fun deleteAll()
}