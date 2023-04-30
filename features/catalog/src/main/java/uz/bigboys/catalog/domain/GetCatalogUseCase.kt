package uz.bigboys.catalog.domain

import uz.bigboys.catalog.domain.entities.ProductFilter
import uz.bigboys.catalog.domain.entities.ProductWithCartInfo
import uz.bigboys.catalog.domain.repositories.CartRepository
import uz.bigboys.catalog.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import uz.bigboys.common.Container
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val cartRepository: CartRepository
) {

    /**
     * Listen for all products which match the specified filter.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProducts(filter: ProductFilter): Flow<Container<List<ProductWithCartInfo>>> {
        return combine(
            productsRepository.getProducts(filter),
            cartRepository.getProductIdentifiersInCart(),
        ) { productsContainer, idsInCartContainer ->
            if (productsContainer !is Container.Success) return@combine productsContainer.map()
            if (idsInCartContainer !is Container.Success) return@combine idsInCartContainer.map()
            val products = productsContainer.value
            val idsInCart = idsInCartContainer.value
            val productsWithCartInfo = products.map {
                ProductWithCartInfo(it, idsInCart.contains(it.id))
            }
            return@combine Container.Success(productsWithCartInfo)
        }
    }

}