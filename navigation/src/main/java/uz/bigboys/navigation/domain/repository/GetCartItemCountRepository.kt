package uz.bigboys.navigation.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container

interface GetCartItemCountRepository {

   /**
    * Listen for the count of items added to the cart.
    * @return infinite flow, always success; errors are delivered to [Container]
    */
   fun getCartItemCount(): Flow<Container<Int>>
}