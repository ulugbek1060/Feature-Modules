package uz.bigboys.multomodulewithfeatures.glue.orders.factories

import uz.bigboys.orders.domain.entities.Price
import uz.bigboys.orders.domain.factories.PriceFactory
import uz.bigboys.multomodulewithfeatures.fromatter.PriceFormatter
import uz.bigboys.multomodulewithfeatures.glue.orders.entity.OrderUsdPrice
import javax.inject.Inject

class DefaultOrderPriceFactory @Inject constructor(
    private val priceFormatter: PriceFormatter,
) : PriceFactory {

    override val zero: Price
        get() = OrderUsdPrice(usdCents = 0, formatter = priceFormatter)
}