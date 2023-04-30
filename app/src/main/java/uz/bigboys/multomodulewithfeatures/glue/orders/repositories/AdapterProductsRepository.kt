package uz.bigboys.multomodulewithfeatures.glue.orders.repositories

import uz.bigboys.orders.domain.repositories.ProductsRepository
import uz.bigboys.data.ProductsDataRepository
import javax.inject.Inject

class AdapterProductsRepository @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
) : ProductsRepository {

    override suspend fun changeQuantityBy(productId: Long, byValue: Int) {
        productsDataRepository.changeQuantityBy(productId, byValue)
    }
}