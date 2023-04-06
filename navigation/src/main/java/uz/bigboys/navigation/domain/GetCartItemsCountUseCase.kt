package uz.bigboys.navigation.domain

import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container
import uz.bigboys.navigation.domain.repository.GetCartItemCountRepository
import javax.inject.Inject

class GetCartItemsCountUseCase @Inject constructor(
   private val getCartItemCountRepository: GetCartItemCountRepository,
) {

   /**
    * Listen for the count of items added to the cart.
    * @return infinite flow, always success; errors are delivered to [Container]
    */
   operator fun invoke(): Flow<Container<Int>> {
      return getCartItemCountRepository.getCartItemCount()
   }
}