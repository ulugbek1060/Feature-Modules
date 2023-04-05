package uz.bigboys.data.cart

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.bigboys.common.Container
import uz.bigboys.common.NotFoundException
import uz.bigboys.common.flow.DefaultLazyFlowSubjectFactory
import uz.bigboys.data.CartDataRepository
import uz.bigboys.data.cart.entity.CartItemDataEntity
import uz.bigboys.data.cart.source.CartDataSource
import uz.bigboys.data.settings.SettingsDataSource
import javax.inject.Inject

class RealCartDataRepository @Inject constructor(
   private val cartSource: CartDataSource,
   private val settingsDataSource: SettingsDataSource,
   scope: CoroutineScope,
   lazyFlowSubjectFactory: DefaultLazyFlowSubjectFactory
) : CartDataRepository {


   init {
      scope.launch {
         settingsDataSource.listenToken().collect {
            if (it == null) {
               cartSource.clearCart()
               cartSubject.updateWith(Container.Success(emptyList()))
            }
         }
      }
   }

   private val cartSubject = lazyFlowSubjectFactory.create {
      cartSource.getCart()
   }

   override fun getCart(): Flow<Container<List<CartItemDataEntity>>> {
      return cartSubject.listen()
   }

   override suspend fun addToCart(productId: Long, quantity: Int) {
      cartSource.saveToCart(productId, quantity)
      notifyChanges()
   }

   override suspend fun getCartItemById(id: Long): CartItemDataEntity {
      return cartSource.getCart().firstOrNull { it.id == id } ?: throw NotFoundException()
   }

   override suspend fun deleteCartItem(ids: List<Long>) {
      ids.forEach { cartSource.delete(it) }
      cartSubject.newAsyncLoad(silently = true)
   }

   override suspend fun deleteAll() {
      cartSource.deleteAll()
      cartSubject.newAsyncLoad(silently = true)
   }

   override suspend fun changeQuantity(cartId: Long, quantity: Int) {
      val cartItem = getCartItemById(cartId)
      val productId = cartItem.productId
      cartSource.saveToCart(productId, quantity)
      cartSubject.newAsyncLoad(silently = true)
   }

   override fun reload() {
      cartSubject.newAsyncLoad()
   }

   private suspend fun notifyChanges() {
      cartSubject.updateWith(Container.Success(cartSource.getCart()))
   }

}