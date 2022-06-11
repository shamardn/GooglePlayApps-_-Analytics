import model.App

fun main() {
    var appsList = emptyList<App>()
    var whatToChoose: String
    println("Choose the file you want to analyse: \n 1. CSV File \n 2. JSON File")
    do {
        println("Type 1 for CSV File or Type 2 for JSON File:")
        whatToChoose = readLine()!!.toString()
        when(whatToChoose) {
            "1" -> appsList = CSVParser().getAllApps()
            "2" -> appsList = JSONParser().getAllApps()
            else -> println("Error: Unknown Input, you can only type 1 or 2")
        }
    } while (!listOf("1", "2").contains(whatToChoose))

    whatToChoose = if(whatToChoose == "1") "CSV" else "JSON"

    println("**************** $whatToChoose File Analytics ****************\n")
    println(appsList)
    println("\n-----------------------------------------------------")

    var companyName = "Google"
    println("Apps Developed by $companyName count is: ${AppAnalyzer().getNumberOfAppsDevelopedBySomeCompany(appsList, companyName)}")
    println("-----------------------------------------------------")

    val category = "Medical"
    println("Percentage of apps have $category category is: ${AppAnalyzer().getAppsByCategoryPercentage(appsList, category)}")
    println("-----------------------------------------------------")

    println("The oldest app is: ${AppAnalyzer().getOldestApp(appsList)}")
    println("-----------------------------------------------------")

    val requiredAndroid = 9.0
    println("Percentage of apps require android version $requiredAndroid and up is: ${AppAnalyzer().getPercentageOfRequiredAndroid(appsList, requiredAndroid)}")
    println("-----------------------------------------------------")

    println("Largest Ten apps are: ${AppAnalyzer().getLargest10Apps(appsList)}")
    println("-----------------------------------------------------")

    println("Top ten installed apps are: ${AppAnalyzer().getTop10InstalledApps(appsList)}")
    println("-----------------------------------------------------")

    companyName = "Meta Platforms Inc."
    println("Largest app developed by $companyName is: ${AppAnalyzer().getLargestAppSizeDevelopedBySomeCompany(appsList, companyName)}")
    println("-----------------------------------------------------")

    println("************** End of $whatToChoose File Analytics **************\n")


    println("\n************** Extensions Functions Start **************")
    println("-----------------------------------------------------")
    val sizeString = "1.1G"
    println("Size in double from string is: ${sizeString.convertSizeUnitToMega()}M")
    println("-----------------------------------------------------")

    val dateString = "June 10 2022"
    println("Date type from string is: ${dateString.toDate()}")
    println("-----------------------------------------------------")
    println("************** Extensions Functions End **************")

}