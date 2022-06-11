import model.App
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class JSONParser : DataSource {
    override fun getAllApps(): List<App> {
        val appsList = mutableListOf<App>()
        JSONArray(File(javaClass.getResource("google_play.json")!!.path).readText()).forEach {
            JSONObject(it.toString()).run {
                appsList.add(
                    App(
                        appName = getString(JSONKeysConstants.APP_NAME),
                        company = getString(JSONKeysConstants.COMPANY),
                        category = getString(JSONKeysConstants.CATEGORY),
                        updated = convertStringToDate(getString(JSONKeysConstants.UPDATED)),
                        size = convertToMegaByte(getString(JSONKeysConstants.SIZE)),
                        installs = getLong(JSONKeysConstants.INSTALLS),
                        requiresAndroid = arrangeRequiresAndroidData(getString(JSONKeysConstants.REQUIRES_ANDROID))
                    )
                )
            }
        }
        return appsList
    }
}