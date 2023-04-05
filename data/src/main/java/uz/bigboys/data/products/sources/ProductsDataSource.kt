package uz.bigboys.data.products.sources

import uz.bigboys.data.products.entities.ProductDataEntity
import uz.bigboys.data.products.entities.ProductDataFilter

interface ProductsDataSource {

   suspend fun getProducts(filter: ProductDataFilter): List<ProductDataEntity>

   suspend fun getProductById(id: Long): ProductDataEntity

   suspend fun getAllCategories(): List<String>

   suspend fun getDiscountPriceUsdCentsForEntity(product: ProductDataEntity): Int?

   suspend fun changeQuantityBy(id: Long, quantityBy: Int)

}