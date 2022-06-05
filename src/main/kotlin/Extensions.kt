import java.text.SimpleDateFormat
import java.util.*

fun String.convertSizeStringToMega(): Double?{
    var size = 0.0
    if (this == "Varies with device") return size
    if (this.contains("M")) {
        size = this.replace("M", "").toDouble()
    } else if (this.contains("K")) {
        val num = (this.replace("K", "").toDouble() / 1024.0)
        size = String.format("%.4f",num).toDouble()
    } else if (this.contains("G")) {
        size = this.replace("G", "").toDouble() * 1024.0
    }
    return size
}

fun String.convertDateStringToDateObject(): Date?{
    val formatter = SimpleDateFormat("MMMM d yyyy")
    val date = formatter.parse(this)
    return date
}