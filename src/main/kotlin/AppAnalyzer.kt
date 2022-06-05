import model.App
import java.text.SimpleDateFormat
import java.util.*

class AppAnalyzer {

    fun numberOfAppsDevelopedByGoogle(appsList: MutableList<App>)
            = appsList.filter { it.company.contains("Google") }.size

    fun medicalAppsPercentage(appsList: MutableList<App>): Double {
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.category == "Medical") counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }

    fun getOldestApp(appsList: List<App>): App? {
        return if (appsList.isEmpty()) {
            null
        } else {
            var oldestApp = appsList[0]
            appsList.forEach {
                if (it.updated < oldestApp.updated) {
                    oldestApp = it
                }
            }
            oldestApp
        }
    }

    fun percentageOfAndroid9AndUp(appsList: MutableList<App>): Double{
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.requiresAndroid == "9 and up") counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }

    fun getLargest10Apps(appList: List<App>): MutableList<App>? {
        if (appList.isEmpty()) return null

        val largest10AppsList = mutableListOf<App>()
        if (appList.size > 9) {
            appList.sortedByDescending { it.size }.subList(0, 10).forEach {
                largest10AppsList.add(it)
            }
        } else {
            appList.sortedByDescending { it.size }.forEach {
                largest10AppsList.add(it)
            }
        }
        return largest10AppsList
    }

    fun getTop10InstalledApps(appsList: MutableList<App>): MutableList<App>? {
        if (appsList.isEmpty()) return null

        val listOfTopApps: MutableList<App> = mutableListOf()
        if (appsList.size > 9) {
            appsList.sortedByDescending { it.installs }.subList(0, 10).forEach {
                listOfTopApps.add(it)
            }
        } else {
            appsList.sortedByDescending { it.installs }.forEach {
                listOfTopApps.add(it)
            }
        }
        return listOfTopApps
    }

    fun getLargestAppSizeDevelopedByMeta(appsList: MutableList<App>): App? {
        return if(appsList.isNotEmpty()) {
            appsList.filter {
                it.company.contains("Meta Platforms Inc")
            }.maxByOrNull { it.size }
        }else{
            null
        }
    }
}
