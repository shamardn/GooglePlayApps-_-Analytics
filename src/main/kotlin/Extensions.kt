import java.text.SimpleDateFormat
import java.util.*

fun String.convertSizeUnitToMega(): Double {
    return when {
        this.contains("M") -> this.replace("M", "").toDouble()
        this.contains("K") -> {
            val num = (this.replace("K", "").toDouble() / 1024.0)
            String.format("%.4f", num).toDouble()
        }
        this.contains("G") -> this.replace("G", "").toDouble() * 1024.0
        else -> 0.0
    }
}

fun String.toDate(): Date? = SimpleDateFormat("MMMM d yyyy").parse(this)