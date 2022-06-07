fun main() {
    val appsList = CSVParser().getAllApps()

//  println(arrangeRequiresAndroidData("Varies with device"))
//  println(arrangeRequiresAndroidData("10-Nov"))
//  println(arrangeRequiresAndroidData(""))
//  println(arrangeRequiresAndroidData("9 and up"))
//  println(arrangeRequiresAndroidData("4.5 and up")) -> ["4.5", "and", "up"]
//  println(arrangeRequiresAndroidData("5.5"))
//  println(arrangeRequiresAndroidData("5.5.3")) -> ["5", "5", "3"]
//  println(arrangeRequiresAndroidData("5.5w"))

    print(AppAnalyzer().percentageOfAndroid9AndUp(appsList, 7.2))
}