import Constants.APP_NAME
import Constants.CATEGORY
import Constants.COMPANY
import Constants.INSTALLS
import Constants.REQUIRED_ANDROID
import Constants.SIZE
import Constants.UPDATED
import model.App
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CSVParser: DataSource {

    // convert from String to Date
    private fun stringToDate(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("MMMM d yyyy"))
    }

    // convert from String to Long Number
    private fun stringToLongNum(value: String): Long {
        return value.toLong()
    }

    // remove Varies with device from data
    private fun arrangeRequiresAndroidData(value: String): String {
        if (value == "Varies with device") return value.replace("Varies with device", "")
        return value
    }


    // convert from GB or KB to MB
    fun megaByteConverter(value: String): Double {
        var size = 0.0
        // 1. check if it is (Varies with device) return 0
        if (value == "Varies with device") return size
        // 2. check if megabytes then remove it and convert to double
        if (value.contains("M")) {
            size = value.replace("M", "").toDouble()
            // 3. check if kilobytes then remove it and convert to megabyte double
        } else if (value.contains("k")) {
            size = value.replace("k", "").toDouble() / 1024.0
            // 4. check if gigabyte then remove it and convert to megabyte double
        } else if (value.contains("G")) {
            size = value.replace("G", "").toDouble() * 1024.0
        }
        return size
    }

    override fun getAllApps(): List<App> {
        val file = File(javaClass.getResource("google_play.csv")!!.path)
        val appsList = mutableListOf<App>()
        file.forEachLine {
            val apps = it.split(",")
            appsList.add(
                App(
                    appName = apps[APP_NAME],
                    company = apps[COMPANY],
                    category = apps[CATEGORY],
                    updated = stringToDate(apps[UPDATED]),
                    size = megaByteConverter(apps[SIZE]),
                    installs = stringToLongNum(apps[INSTALLS]),
                    requiresAndroid = arrangeRequiresAndroidData(apps[REQUIRED_ANDROID]),
                )
            )
        }
        return appsList
    }
}