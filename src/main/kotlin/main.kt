fun main() {
    println("**************** Apps List Analytics ****************\n")
    val appsList = CSVParser().getAllApps()
    println(appsList)
    println("\n*****************************************************\n")

    var companyName = "Google"
    println("Apps Developed by $companyName count is: ${AppAnalyzer().getNumberOfAppsDevelopedBySomeCompany(appsList, companyName)}")
    println("\n*****************************************************\n")

    val category = "Medical"
    println("Percentage of apps have $category category is: ${AppAnalyzer().getAppsByCategoryPercentage(appsList, category)}")
    println("\n*****************************************************\n")

    println("The oldest app is: ${AppAnalyzer().getOldestApp(appsList)}")
    println("\n*****************************************************\n")

    val requiredAndroid = 9.0
    println("Percentage of apps require android version $requiredAndroid and up is: ${AppAnalyzer().getPercentageOfRequiredAndroid(appsList, requiredAndroid)}")
    println("\n*****************************************************\n")

    println("Largest Ten apps are: ${AppAnalyzer().getLargest10Apps(appsList)}")
    println("\n*****************************************************\n")

    println("Top ten installed apps are: ${AppAnalyzer().getTop10InstalledApps(appsList)}")
    println("\n*****************************************************\n")

    companyName = "Meta Platforms Inc."
    println("Largest app developed by $companyName is: ${AppAnalyzer().getLargestAppSizeDevelopedBySomeCompany(appsList, companyName)}")
    println("\n************** End of Apps List Analytics **************\n")

    println("\n************** Extensions Functions Start **************\n")

    val sizeString = "1.1G"
    println("Size in double from string is: ${sizeString.convertSizeUnitToMega()}M")
    println("\n*****************************************************\n")

    val dateString = "June 10 2022"
    println("Date type from string is: ${dateString.toDate()}")

}