import model.App

class AppAnalyzer {

    fun getNumberOfAppsDevelopedBySomeCompany(appsList: MutableList<App>, companyName: String) =if(appsList.isEmpty()) null else appsList.filter { it.company.contains(companyName) }.size

    fun medicalAppsPercentage(appsList: MutableList<App>, category: String): Double {
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.category.contains(category)) counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }

    fun getOldestApp(appsList: List<App>) =if (appsList.isEmpty()) null else appsList.sortedBy { it.updated  }.subList(0,1)

    fun percentageOfAndroid9AndUp(appsList: List<App>,androidRequired: Double): Double{
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.requiresAndroid == androidRequired) counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }

    fun getLargest10Apps(appList: List<App>) =if (appList.isEmpty()) null else appList.sortedByDescending { it.size }.subList(0, 10)

    fun getTop10InstalledApps(appsList: MutableList<App>)= if (appsList.isEmpty()) null else appsList.sortedByDescending { it.installs }.subList(0, 10)

    fun getLargestAppSizeDevelopedBySomeCompany(appsList: MutableList<App>, companyName: String)= if (appsList.isEmpty()) null else appsList.filter { it.company.contains(companyName)}.maxByOrNull { it.size }

}

