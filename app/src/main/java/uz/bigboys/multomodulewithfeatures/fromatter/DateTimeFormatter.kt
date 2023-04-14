package uz.bigboys.multomodulewithfeatures.fromatter

interface DateTimeFormatter {

    /**
     * Covert timestamp in milliseconds to a user-friendly date-time string.
     */
    fun formatDateTime(millis: Long): String

}