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
                    appName = apps[AppPropertiesConstants.APP_NAME_INDEX],
                    company = apps[AppPropertiesConstants.COMPANY_INDEX],
                    category = apps[AppPropertiesConstants.CATEGORY_INDEX],
                    updated = convertStringToDate(apps[AppPropertiesConstants.UPDATED_INDEX]),
                    size = convertToMegaByte(apps[AppPropertiesConstants.SIZE_INDEX]),
                    installs = convertStringToLongNum(apps[AppPropertiesConstants.INSTALLS_INDEX]),
                    requiresAndroid = arrangeRequiresAndroidData2(apps[AppPropertiesConstants.REQUIRED_ANDROID_INDEX])
                )
            )
        }
        return appsList
    }
}