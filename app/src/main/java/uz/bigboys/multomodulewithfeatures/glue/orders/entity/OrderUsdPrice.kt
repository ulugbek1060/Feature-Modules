package uz.bigboys.multomodulewithfeatures.glue.orders.entity

import uz.bigboys.orders.domain.entities.Price
import uz.bigboys.multomodulewithfeatures.fromatter.PriceFormatter

class OrderUsdPrice(
    val usdCents: Int,
    private val formatter: PriceFormatter,
) : Price {

    override val text: String
        get() = formatter.formatPrice(usdCents)

    override fun plus(price: Price): Price {
        return OrderUsdPrice(
            usdCents = usdCents + (price as OrderUsdPrice).usdCents,
            formatter = formatter
        )
    }
}