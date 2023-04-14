package uz.bigboys.multomodulewithfeatures.fromatter

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultPriceFormatter @Inject constructor() : PriceFormatter {

    override fun formatPrice(usdCents: Int): String {
        return "\$${String.format("%.2f", usdCents / 100f)}"
    }

}