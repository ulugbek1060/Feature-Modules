package uz.bigboys.multomodulewithfeatures.glue.catalog.repositories

import com.example.catalog.domain.entities.Price
import com.example.catalog.domain.entities.Product
import com.example.catalog.domain.entities.ProductFilter
import com.example.catalog.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.bigboys.common.Container
import uz.bigboys.data.ProductsDataRepository
import uz.bigboys.multomodulewithfeatures.fromatter.PriceFormatter
import uz.bigboys.multomodulewithfeatures.glue.catalog.entities.CatalogUsdPrice
import uz.bigboys.multomodulewithfeatures.glue.catalog.mappers.ProductFilterMapper
import uz.bigboys.multomodulewithfeatures.glue.catalog.mappers.ProductMapper
import javax.inject.Inject

class AdapterProductsRepository @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val productMapper: ProductMapper,
    private val productFilterMapper: ProductFilterMapper,
    private val priceFormatter: PriceFormatter,
) : ProductsRepository {

    override fun getProducts(filter: ProductFilter): Flow<Container<List<Product>>> {
        val dataFilter = productFilterMapper.toProductDataFilter(filter)
        return productsDataRepository.getProducts(dataFilter).map { container ->
            container.suspendMap { list ->
                list.map { dataEntity ->
                    productMapper.toProduct(dataEntity)
                }
            }
        }
    }

    override suspend fun getProduct(id: Long): Product {
        val productDataEntity = productsDataRepository.getProductById(id)
        return productMapper.toProduct(productDataEntity)
    }

    override suspend fun getMinPossiblePrice(): Price {
        return CatalogUsdPrice(productsDataRepository.getMinPriceUsdCents(), priceFormatter)
    }

    override suspend fun getMaxPossiblePrice(): Price {
        return CatalogUsdPrice(productsDataRepository.getMaxPriceUsdCents(), priceFormatter)
    }

    override suspend fun getAllCategories(): List<String> {
        return productsDataRepository.getAllCategories()
    }

}