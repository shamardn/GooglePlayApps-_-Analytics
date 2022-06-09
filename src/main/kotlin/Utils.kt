import java.time.LocalDate
import java.time.format.DateTimeFormatter

// convert from String to Date
fun convertStringToDate(value: String): LocalDate {
    return LocalDate.parse(value, DateTimeFormatter.ofPattern("MMMM d yyyy"))
}

// convert from String to Long Number
fun convertStringToLongNum(value: String): Long {
    return value.toLong()
}

fun arrangeRequiresAndroidData(value: String): Double {
    if (value == "10-Nov") return -1.0
    val answer = value.replace("\\D".toRegex(), "")
    return when (answer.length) {
        0 -> 0.0                // "Varies with device"
        1 -> answer.toDouble()
        2 -> answer.toDouble() / 10  //4.4 ->  44 /10  -> 4.4
        else -> String.format("%.2f",answer.toDouble() / 100).toDouble()   //
    }
}

// convert from GB or KB to MB
fun convertToMegaByte(value: String): Double {
    var size = 0.0
    if (value == "Varies with device") return size
    if (value.contains("M")) {
        size = value.replace("M", "").toDouble()
    } else if (value.contains("K")) {
        val num = (value.replace("K", "").toDouble() / 1024.0)
        size = String.format("%.4f", num).toDouble()
    } else if (value.contains("G")) {
        size = value.replace("G", "").toDouble() * 1024.0
    }
    return size
}