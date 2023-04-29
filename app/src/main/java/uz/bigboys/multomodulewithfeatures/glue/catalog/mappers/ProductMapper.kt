package uz.bigboys.multomodulewithfeatures.glue.catalog.mappers

import com.example.catalog.domain.entities.Product
import uz.bigboys.data.ProductsDataRepository
import uz.bigboys.data.products.entities.ProductDataEntity
import uz.bigboys.multomodulewithfeatures.fromatter.PriceFormatter
import uz.bigboys.multomodulewithfeatures.glue.catalog.entities.CatalogUsdPrice
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val priceFormatter: PriceFormatter,
) {

    suspend fun toProduct(dataEntity: ProductDataEntity): Product {
        val discountPrice = productsDataRepository.getDiscountPriceUsdCentsForEntity(dataEntity)?.let {
            CatalogUsdPrice(it, priceFormatter)
        }
        return Product(
            id = dataEntity.id,
            name = dataEntity.name,
            shortDetails = dataEntity.shortDescription,
            details = dataEntity.description,
            category = dataEntity.category,
            price = CatalogUsdPrice(dataEntity.priceUsdCents, priceFormatter),
            priceWithDiscount = discountPrice,
            photo = dataEntity.imageUrl
        )
    }

}