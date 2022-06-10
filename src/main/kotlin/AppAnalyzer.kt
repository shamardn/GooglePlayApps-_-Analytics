import model.App

class AppAnalyzer {

    fun getNumberOfAppsDevelopedBySomeCompany(appsList: List<App>, companyName: String) = if(appsList.isEmpty()) 0 else appsList.filter { it.company.contains(companyName) }.size

    fun getAppsByCategoryPercentage(appsList: List<App>, category: String): Double {
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.category.contains(category)) counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }

    fun getOldestApp(appsList: List<App>) = if(appsList.isEmpty()) null else appsList.sortedBy { it.updated  }[0]

    fun getPercentageOfRequiredAndroid(appsList: List<App>,androidRequired: Double): Double{
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.requiresAndroid == androidRequired) counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }

    fun getLargest10Apps(appList: List<App>) = if(appList.isEmpty() || appList.size < 10) null else appList.sortedByDescending { it.size }.subList(0, 10)

    fun getTop10InstalledApps(appsList: List<App>) = if(appsList.isEmpty() || appsList.size < 10) null else appsList.sortedByDescending { it.installs }.subList(0, 10)

    fun getLargestAppSizeDevelopedBySomeCompany(appsList: List<App>, companyName: String) = if(appsList.isEmpty()) null else appsList.filter { it.company.contains(companyName)}.maxByOrNull { it.size }

}
