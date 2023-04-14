package uz.bigboys.multomodulewithfeatures.fromatter

import uz.bigboys.multomodulewithfeatures.fromatter.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultDateTimeFormatter @Inject constructor() : DateTimeFormatter {

    private val formatter = SimpleDateFormat.getDateTimeInstance()

    override fun formatDateTime(millis: Long): String {
        return formatter.format(Date(millis))
    }

}