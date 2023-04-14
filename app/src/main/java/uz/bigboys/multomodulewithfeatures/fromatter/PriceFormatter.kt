package uz.bigboys.multomodulewithfeatures.fromatter

interface PriceFormatter {

    /**
     * Convert cents into user-friendly price text.
     */
    fun formatPrice(usdCents: Int): String

}