import java.text.SimpleDateFormat
import java.util.*

fun String.convertSizeStringToMega(): Double?{
    var size = 0.0
    // 1. check if it is (Varies with device) return 0
    if (this == "Varies with device") return size
    // 2. check if megabytes then remove it and convert to double
    else if (this.contains("M")) {
        size = this.replace("M", "").toDouble()
        // 3. check if kilobytes then remove it and convert to megabyte double
    } else if (this.contains("K")) {
        val num = (this.replace("K", "").toDouble() / 1024.0)
        size = String.format("%.4f",num).toDouble()
        // 4. check if gigabyte then remove it and convert to megabyte double
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