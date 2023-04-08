package uz.bigboys.multomodulewithfeatures.glue.navigation.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.bigboys.common.Container
import uz.bigboys.data.CartDataRepository
import uz.bigboys.navigation.domain.repository.GetCartItemCountRepository
import javax.inject.Inject

class AdapterGetCartItemCountRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
) : GetCartItemCountRepository {

    override fun getCartItemCount(): Flow<Container<Int>> {
        return cartDataRepository.getCart().map { container ->
            container.map { list -> list.size }
        }
    }

}