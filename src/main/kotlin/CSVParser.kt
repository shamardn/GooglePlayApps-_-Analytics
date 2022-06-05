import Constants.APP_NAME_INDEX
import Constants.CATEGORY_INDEX
import Constants.COMPANY_INDEX
import Constants.INSTALLS_INDEX
import Constants.REQUIRED_ANDROID_INDEX
import Constants.SIZE_INDEX
import Constants.UPDATED_INDEX
import model.App
import java.io.File

class CSVParser: DataSource {
    override fun getAllApps(): List<App> {
        val file = File(javaClass.getResource("google_play.csv")!!.path)
        val appsList = mutableListOf<App>()
        file.forEachLine {
            val apps = it.split(",")
            appsList.add(
                App(
                    appName = apps[APP_NAME_INDEX],
                    company = apps[COMPANY_INDEX],
                    category = apps[CATEGORY_INDEX],
                    updated = convertStringToDate(apps[UPDATED_INDEX]),
                    size = convertToMegaByte(apps[SIZE_INDEX]),
                    installs = convertStringToLongNum(apps[INSTALLS_INDEX]),
                    requiresAndroid = arrangeRequiresAndroidData(apps[REQUIRED_ANDROID_INDEX]),
                )
            )
        }
        return appsList
    }
}