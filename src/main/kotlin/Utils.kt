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

// remove Varies with device from data
fun arrangeRequiresAndroidData(value: String): Double {
    if (value == "Varies with device" || value == "10-Nov" || value.isEmpty()) return 0.0
    var finalVersion = 0.0

    val version = value.split(" ")[0]
    if(version.length == 1) return "${version}.${0}".toDouble()

    if(version.length > 3){
        val list = version.split(".")
        if(list[1].length > 1) {
            finalVersion = "${list[0]}.${list[1][0]}".toDouble()
        } else {
            finalVersion = "${list[0]}.${list[1]}".toDouble()
        }
    } else {
        finalVersion = version.toDouble()
    }
    return finalVersion
}

// convert from GB or KB to MB
fun convertToMegaByte(value: String): Double {
    var size = 0.0
    if (value == "Varies with device") return size
    if (value.contains("M")) {
        size = value.replace("M", "").toDouble()
    } else if (value.contains("K")) {
        val num = (value.replace("K", "").toDouble() / 1024.0)
        size = String.format("%.4f",num).toDouble()
    } else if (value.contains("G")) {
        size = value.replace("G", "").toDouble() * 1024.0
    }
    return size
}