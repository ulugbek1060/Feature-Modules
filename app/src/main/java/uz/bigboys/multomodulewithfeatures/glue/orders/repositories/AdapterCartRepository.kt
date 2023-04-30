package uz.bigboys.multomodulewithfeatures.glue.orders.repositories

import uz.bigboys.orders.domain.entities.Cart
import uz.bigboys.orders.domain.entities.CartItem
import uz.bigboys.orders.domain.factories.PriceFactory
import uz.bigboys.orders.domain.repositories.CartRepository
import uz.bigboys.common.unwrapFirst
import uz.bigboys.data.CartDataRepository
import uz.bigboys.data.ProductsDataRepository
import uz.bigboys.multomodulewithfeatures.fromatter.PriceFormatter
import uz.bigboys.multomodulewithfeatures.glue.orders.entity.OrderUsdPrice
import javax.inject.Inject

class AdapterCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    private val productsDataRepository: ProductsDataRepository,
    private val priceFormatter: PriceFormatter,
    private val priceFactory: PriceFactory,
) : CartRepository {

    override suspend fun getCart(): Cart {
        val cartItems = cartDataRepository.getCart().unwrapFirst()
        return Cart(
            cartItems = cartItems.map {
                val product = productsDataRepository.getProductById(it.productId)
                val priceWithDiscount =
                    productsDataRepository.getDiscountPriceUsdCentsForEntity(product)
                        ?: product.priceUsdCents
                CartItem(
                    name = product.name,
                    productId = it.productId,
                    price = OrderUsdPrice(priceWithDiscount, priceFormatter),
                    quantity = it.quantity,
                )
            },
            priceFactory = priceFactory,
        )
    }

    override suspend fun cleanUpCart() {
        cartDataRepository.deleteAll()
    }
}