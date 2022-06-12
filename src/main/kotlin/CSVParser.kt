import model.App
import java.io.File

class CSVParser: DataSource {

    override fun getAllApps(): List<App> {
        val appsList = mutableListOf<App>()
        File(javaClass.getResource("google_play.csv")!!.path).forEachLine {
            it.split(",").run {
                appsList.add(
                    App(
                        appName = this[AppPropertiesConstants.APP_NAME_INDEX],
                        company = this[AppPropertiesConstants.COMPANY_INDEX],
                        category = this[AppPropertiesConstants.CATEGORY_INDEX],
                        updated = convertStringToDate(this[AppPropertiesConstants.UPDATED_INDEX]),
                        size = convertToMegaByte(this[AppPropertiesConstants.SIZE_INDEX]),
                        installs = convertStringToLongNum(this[AppPropertiesConstants.INSTALLS_INDEX]),
                        requiresAndroid = arrangeRequiresAndroidData(this[AppPropertiesConstants.REQUIRED_ANDROID_INDEX])
                    )
                )
            }
        }
        return appsList
    }
}