import model.App
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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


    val list = mutableListOf<App>()
    list.add(App("", "","",
        LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
        15.0,122,9.0))
    print(AppAnalyzer().percentageOfAndroid9AndUp(appsList, arrangeRequiresAndroidData2("4.4w and up")))

}