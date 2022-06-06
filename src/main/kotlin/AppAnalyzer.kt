import model.App

class AppAnalyzer {

    fun getNumberOfAppsDevelopedByGoogle(appsList: MutableList<App>, companyName: String)
            = appsList.filter { it.company.contains(companyName) }.size

    fun medicalAppsPercentage(appsList: MutableList<App>, category: String): Double {
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.category == category) counter++
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

    fun percentageOfAndroid9AndUp(appsList: MutableList<App>,androidRequired: Double): Double{
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.requiresAndroid == androidRequired) counter++
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

    fun getLargestAppSizeDevelopedByMeta(appsList: MutableList<App>,companyName: String): App? {
        return if(appsList.isNotEmpty()) {
            appsList.filter {
                it.company.contains(companyName)
            }.maxByOrNull { it.size }
        }else{
            null
        }
    }
}
