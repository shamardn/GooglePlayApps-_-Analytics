import model.App
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AppAnalyzerTest{

    private lateinit var appAnalyzer: AppAnalyzer

    @BeforeAll
    fun setup() {
        appAnalyzer = AppAnalyzer()
    }

    // region test functions for numberOfAppsDevelopedBySomeCompany function
    @Test
    fun should_ReturnNumber1_When_HaveListWith1GoogleApp() {
        // given list contain one "Google" word
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "Hutch Games", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                arrangeRequiresAndroidData("4.4 and up")
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedBySomeCompany(appList,"Hutch Games")
        // then check the result
        assertEquals(1, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveListWithNoAnyGoogleApp() {
        // given list that doesn't contain the word Google
        val appList= mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "Hutch Games", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                arrangeRequiresAndroidData("4.4 and up")
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedBySomeCompany(appList,"Google")
        // then check the result
        assertEquals(0, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyCompanyValue() {
        // given empty company value
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                arrangeRequiresAndroidData("4.4 and up")
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedBySomeCompany(appList,"Google")
        // then check the result
        assertEquals(0, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyAppsList(){
        // given empty apps list
        val appList = emptyList<App>()

        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedBySomeCompany(appList,"Google")

        // then check the result
        assertEquals(0, numberOfApps)
    }

    // endregion

    // region test functions for medicalAppsPercentage function
    @Test
    fun should_ReturnHundredPercent_When_HaveListOfMedicalAppsOnly() {
        // given list of apps with 100% medical apps
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Medical",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                arrangeRequiresAndroidData("1.6 and up")
            )
        )
        // when calculate percentage
        val percentage = appAnalyzer.getAppsByCategoryPercentage(appList,"Medical")
        // then check the result
        assertEquals(100.0, percentage)
    }

    @Test
    fun should_Return33point33Percent_When_MedicalAppsAreOneThirdOfAppsList() {
        // given list of apps with 33.33% medical apps
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Medical Mnemonics", "Regular Rate and Rhythm Software", "Medical",
                    LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                    arrangeRequiresAndroidData("1.6 and up")
                )
            )
            add(
                App(
                    "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                    "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    51.0, 2000, arrangeRequiresAndroidData("8.0 and up")
                )
            )
            add(
                App(
                    "Eyes : Nonogram","GAMEFOX", "Puzzle",
                    LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    51.0,1500,arrangeRequiresAndroidData("5.0 and up"))
            )
        }
        // when calculate percentage
        val percentage = appAnalyzer.getAppsByCategoryPercentage(appList, "Medical")
        // then check the result
        assertEquals(33.33, percentage)
    }

    @Test
    fun should_ReturnZero_When_NoMedicalAppInTheAppsList() {
        // given list of apps with zero medical apps
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Medical Mnemonics", "Regular Rate and Rhythm Software", "Photography",
                    LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                    arrangeRequiresAndroidData("1.6 and up")
                )
            )
            add(
                App(
                    "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                    "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    51.0, 2000, arrangeRequiresAndroidData("8.0 and up")
                )
            )
        }
        // when calculate percentage
        val percentage = appAnalyzer.getAppsByCategoryPercentage(appList, "Medical")
        // then check the result
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyList() {
        // given empty list
        val appList = emptyList<App>()

        // when calculate percentage
        val percentage = appAnalyzer.getAppsByCategoryPercentage(appList, "Medical")

        // then check the result
        assertEquals(0.0, percentage)
    }

    // endregion

    // region test functions for getOldestApp function
    @Test
    fun should_ReturnFirstApp_When_ListHasOnlyOneApp() {
        // given list has one app
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Medical",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                arrangeRequiresAndroidData("1.6w and up")
            )
        )
        // when
        val oldestApp = appAnalyzer.getOldestApp(appList)
        // then check the result
        assertEquals(appList[0], oldestApp)
    }

    @Test
    fun should_ReturnOldestApp_When_HaveListWithMultiApps() {
        // given list of apps
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Jewel Blast : Temple", "", "Puzzle",
                    LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                    arrangeRequiresAndroidData("4.4 and up")
                )
            )
            add(
                App(
                    "AD Books", "Daovude", "Libraries & Demo",
                    LocalDate.parse("January 10 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    30.0, 5, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "myAudi", "Audi", "Auto & Vehicles",
                    LocalDate.parse("May 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    53.0, 1250, arrangeRequiresAndroidData("8.0 and up")
                )
            )
        }
        // when
        val oldestApp = appAnalyzer.getOldestApp(appList)
        // then check the result
        assertEquals(appList[1], oldestApp)
    }

    @Test
    fun should_ReturnNull_When_HaveEmptyList() {
        // given empty list
        val appList = emptyList<App>()
        // when
        val oldestApp = appAnalyzer.getOldestApp(appList)
        // then check the result
        assertNull(oldestApp)
    }

    // endregion

    // region test functions for percentageOfAndroid9AndUp function
    @Test
    fun should_ReturnZero_When_ListHasNotAppRequiredAndroid9AndUp() {
        //given
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                arrangeRequiresAndroidData("4.0 and up")
            )
        )
        //when
        val percentage = appAnalyzer.getPercentageOfRequiredAndroid(appList, arrangeRequiresAndroidData("9 and up"))

        //then
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_Return_33point33_When_ListHaveAppsRequiredAndroid9AndUp() {
        //given
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Jewel Blast : Temple", "", "Puzzle",
                    LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                    arrangeRequiresAndroidData("9 and up")
                )
            )
            add(
                App(
                    "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                    LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                    arrangeRequiresAndroidData("4.4 and up")
                )
            )
            add(
                App(
                    "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                    "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    98.1, 2000, arrangeRequiresAndroidData("8.0 and up")
                )
            )
        }
        //when
        val percentage = appAnalyzer.getPercentageOfRequiredAndroid(appList,arrangeRequiresAndroidData("9 and up"))

        //then
        assertEquals(33.33, percentage)
    }

    @Test
    fun should_ReturnZero_When_ListIsEmpty() {
        //given
        val appList = emptyList<App>()

        //when
        val percentage = appAnalyzer.getPercentageOfRequiredAndroid(appList, arrangeRequiresAndroidData("7 and up"))

        //then
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_Return_Point8_When_Use_CsvList(){
        //given
        val appList = CSVParser().getAllApps()

        //when
        val percentage = appAnalyzer.getPercentageOfRequiredAndroid(appList, arrangeRequiresAndroidData("9 and up"))

        //then
        assertEquals(0.8, percentage)
    }
    // endregion

    // region test functions for getLargest10Apps function
    @Test
    fun should_ReturnLargest10Apps_When_TheListOfAppsContainsMoreThan9Apps(){
        // given
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                    LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                    arrangeRequiresAndroidData("4.4 and up")
                )
            )
            add(
                App(
                    "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                    "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    0.11, 2000, arrangeRequiresAndroidData("8.0 and up")
                )
            )
            add(
                App(
                    "Eyes : Nonogram", "GAMEFOX", "Puzzle",
                    LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    0.23, 1500, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Real Drift Car Racing", "Real Games srls", "Racing",
                    LocalDate.parse("March 26 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    0.74, 500, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                    LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    2.2, 2500, arrangeRequiresAndroidData("7.0 and up")
                )
            )
            add(
                App(
                    "Angel Saga: Hero Action RPG", "Alchemist Games Inc.", "Action",
                    LocalDate.parse("April 1 2020", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    58.0, 200, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Crazy Pusher", "Borg Studio", "Casino",
                    LocalDate.parse("February 2 2019", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    112.8, 3000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                    LocalDate.parse("December 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    532.0, 2800, arrangeRequiresAndroidData("5.2 and up")
                )
            )
            add(
                App(
                    "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                    LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    691.3, 2600, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Manta: Comics & Graphic Novels", "RIDI Corporation", "Comics",
                    LocalDate.parse("May 16 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    1150.2, 2300, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Sago Mini School (Kids 2-5)", "Sago Mini", "Education",
                    LocalDate.parse("February 24 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    1552.0, 800, arrangeRequiresAndroidData("4.4 and up")
                )
            )
        }

        val listOfLargest10Apps = mutableListOf<App>().apply {
            add(appList[10])
            add(appList[9])
            add(appList[8])
            add(appList[7])
            add(appList[6])
            add(appList[5])
            add(appList[4])
            add(appList[3])
            add(appList[2])
            add(appList[1])
        }

        // when fined the largest10 app of the list
        val result = appAnalyzer.getLargest10Apps(appList)
        // then
        assertEquals(listOfLargest10Apps, result)
    }

    @Test
    fun should_ReturnNull_When_TheListOfAppsContainsLessThan10Apps() {
        // given list of apps have 5 element
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                    LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("923.2K"), 2500, arrangeRequiresAndroidData("7.0 and up")
                )
            )
            add(
                App(
                    "Crazy Pusher", "Borg Studio", "Casino",
                    LocalDate.parse("March 25 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("123.2M"), 3000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                    LocalDate.parse("March 9 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("522.0M"), 2800, arrangeRequiresAndroidData("5.2 and up")
                )
            )
            add(
                App(
                    "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                    LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("600.2M"), 2600, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Dinosaur Airport:Game for kids", "Yateland - Learning Games For Kids", "Educational",
                    LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("1001.0M"), 4000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
        }
        val result = appAnalyzer.getLargest10Apps(appList)
        // then
        assertNull(result)
    }

    @Test
    fun should_ReturnNull_When_TheListOfAppsIsEmpty() {
        // given empty list of apps
        val appList = emptyList<App>()

        // when find the top installed apps name
        val result = appAnalyzer.getLargest10Apps(appList)
        // then
        Assertions.assertNull(result)
    }

    // endregion

    // region test functions for getTop10InstalledApps function
    @Test
    fun should_ReturnTop10InstalledApp_When_TheListOfAppsContainsMoreThan9Apps() {
        // given
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                    LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                    arrangeRequiresAndroidData("4.4 and up")
                )
            )
            add(
                App(
                    "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                    "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    0.11, 2000, arrangeRequiresAndroidData("8.0 and up")
                )
            )
            add(
                App(
                    "Eyes : Nonogram", "GAMEFOX", "Puzzle",
                    LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    0.23, 3000, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Real Drift Car Racing", "Real Games srls", "Racing",
                    LocalDate.parse("March 26 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    0.74, 4000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                    LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    2.2, 5000, arrangeRequiresAndroidData("7.0 and up")
                )
            )
            add(
                App(
                    "Angel Saga: Hero Action RPG", "Alchemist Games Inc.", "Action",
                    LocalDate.parse("April 1 2020", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    58.0, 6000, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Crazy Pusher", "Borg Studio", "Casino",
                    LocalDate.parse("February 2 2019", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    112.8, 7000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                    LocalDate.parse("December 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    532.0, 8000, arrangeRequiresAndroidData("5.2 and up")
                )
            )
            add(
                App(
                    "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                    LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    691.3, 9000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Manta: Comics & Graphic Novels", "RIDI Corporation", "Comics",
                    LocalDate.parse("May 16 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    1150.2, 10000, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Sago Mini School (Kids 2-5)", "Sago Mini", "Education",
                    LocalDate.parse("February 24 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    1552.0, 11000, arrangeRequiresAndroidData("4.4 and up")
                )
            )
        }

        val listOfLargest10Apps = mutableListOf<App>().apply {
            add(appList[10])
            add(appList[9])
            add(appList[8])
            add(appList[7])
            add(appList[6])
            add(appList[5])
            add(appList[4])
            add(appList[3])
            add(appList[2])
            add(appList[1])
        }

        // when fined the largest10 app of the list
        val result = appAnalyzer.getTop10InstalledApps(appList)
        // then
        assertEquals(listOfLargest10Apps, result)
    }

    @Test
    fun should_Return_Null_When_TheListOfAppsContainsLessThan10Apps() {
        // given list of apps have 5 elements
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                    LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    23.2, 1000, arrangeRequiresAndroidData("7.1 and up")
                )
            )
            add(
                App(
                    "Crazy Pusher", "Borg Studio", "Casino",
                    LocalDate.parse("March 25 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    1.3, 2000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                    LocalDate.parse("March 9 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    0.52, 3000, arrangeRequiresAndroidData("5.2 and up")
                )
            )
            add(
                App(
                    "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                    LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    52.0, 4000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
            add(
                App(
                    "Dinosaur Airport:Game for kids", "Yateland - Learning Games For Kids", "Educational",
                    LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    762.3, 5000, arrangeRequiresAndroidData("4.1 and up")
                )
            )
        }

        // when
        val result = appAnalyzer.getTop10InstalledApps(appList)
        // then
        assertNull(result)
    }

    @Test
    fun should_Return_Null_When_TheListOfAppsIsEmpty() {
        // given empty list of google play apps
        val appList = emptyList<App>()

        // when
        val result = appAnalyzer.getTop10InstalledApps(appList)

        // then
        assertNull(result)
    }

    // endregion

    // region test functions for (function name) function
    @Test
    fun should_Return_LargestAppByMetaPlatforms(){
        // given list of Apps
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Novi", "Meta Platforms Inc", "Finance",
                    LocalDate.parse("May 5 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("1K"), 50000, arrangeRequiresAndroidData("6.0 and up")
                )
            )
            add(
                App(
                    "Facebook Portal", "Meta Platforms Inc", "Lifestyle",
                    LocalDate.parse("April 14 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("2K"), 100000, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Messenger Kids – The Messaging App for Kids", "Meta Platforms Inc", "Communication",
                    LocalDate.parse("May 17 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("5K"), 10000000, arrangeRequiresAndroidData("Varies with device")
                )
            )
            add(
                App(
                    "Facebook Lite", "Meta Platforms Inc", "Social",
                    LocalDate.parse("May 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("3K"), 1000000000, arrangeRequiresAndroidData("Varies with device")
                )
            )
            add(
                App(
                    "Messenger Lite", "Meta Platforms Inc", "Communication",
                    LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("4K"), 500000000, arrangeRequiresAndroidData("4.0 and up")
                )
            )
        }

        val currentApp = App(
            "Messenger Kids – The Messaging App for Kids", "Meta Platforms Inc", "Communication",
            LocalDate.parse("May 17 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
            convertToMegaByte("5K"), 10000000, arrangeRequiresAndroidData("Varies with device")
        )

        //when find the largest app
        val result = appAnalyzer.getLargestAppSizeDevelopedBySomeCompany(appList,"Meta Platforms Inc")

        // then
        assertEquals(currentApp, result)
    }

    @Test
    fun should_ReturnNull_MetaApps_When_AppsList_IsEmpty() {
        // given empty list of apps
        val appList = emptyList<App>()

        // when find the top installed apps name
        val result = appAnalyzer.getLargestAppSizeDevelopedBySomeCompany(appList,"Meta Platforms Inc")
        // then
        assertNull(result)
    }

    @Test
    fun should_ReturnNull_When_ThereIsNotMetaPlatformApps() {
        // given list has not MetaPlatformApps
        val appList = mutableListOf<App>().apply {
            add(
                App(
                    "Novi", "Pangea Money Transfer", "Finance",
                    LocalDate.parse("May 5 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("1k"), 50000, arrangeRequiresAndroidData("6.0 and up")
                )
            )
            add(
                App(
                    "Facebook Portal", "Pangea Money Transfer", "Lifestyle",
                    LocalDate.parse("April 14 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("2k"), 100000, arrangeRequiresAndroidData("5.0 and up")
                )
            )
            add(
                App(
                    "Messenger Kids – The Messaging App for Kids", "Pangea Money Transfer", "Communication",
                    LocalDate.parse("May 17 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("5k"), 10000000, arrangeRequiresAndroidData("Varies with device")
                )
            )
            add(
                App(
                    "Facebook Lite", "Pangea Money Transfer", "Social",
                    LocalDate.parse("May 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("3k"), 1000000000, arrangeRequiresAndroidData("Varies with device")
                )
            )
            add(
                App(
                    "Messenger Lite", "Pangea Money Transfer", "Communication",
                    LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                    convertToMegaByte("4k"), 500000000, arrangeRequiresAndroidData("4.0 and up")
                )
            )
        }

        // when
        val result = appAnalyzer.getLargestAppSizeDevelopedBySomeCompany(appList,"Meta Platforms Inc")
        // then
        assertNull(result)
    }

    //endregion

}