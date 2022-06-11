import model.App
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class JSONParser : DataSource {
    override fun getAllApps(): List<App> {
        val jsonFile = File(javaClass.getResource("google_play.json")!!.path)
        val jsonArray = JSONArray(jsonFile.readText())
        val appsList = mutableListOf<App>()
        jsonArray.forEach {
            val jsonObj = JSONObject(it.toString())
            appsList.add(
                App(
                    appName = jsonObj.getString("appName"),
                    company = jsonObj.getString("company"),
                    category = jsonObj.getString("category"),
                    updated = convertStringToDate(jsonObj.getString("updated")),
                    size = convertToMegaByte(jsonObj.getString("size")),
                    installs = jsonObj.getLong("installs"),
                    requiresAndroid = arrangeRequiresAndroidData(jsonObj.getString("requiresAndroid"))
                )
            )
        }
        return appsList
    }
}