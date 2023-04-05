package uz.bigboys.data.products.sources

interface DiscountsDataSource {

    suspend fun getDiscountPercentage(productId: Long): Int?

}