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

    // region test functions for numberOfAppsDevelopedByGoogle function
    @Test
    fun should_ReturnNumber1_When_HaveListWith1GoogleApp() {
        // given list contain one "Google" word
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "Google", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "4.4 and up"
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedByGoogle(appList,"Google")
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
                "4.4 and up"
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedByGoogle(appList,"Google")
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
                "4.4 and up"
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedByGoogle(appList,"Google")
        // then check the result
        assertEquals(0, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyAppsList(){
        // given empty apps list
        val appList = mutableListOf<App>()

        // when calculate number of Apps
        val numberOfApps = appAnalyzer.getNumberOfAppsDevelopedByGoogle(appList,"Google")

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
                "1.6 and up"
            )
        )
        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList,"Medical")
        // then check the result
        assertEquals(100.0, percentage)
    }

    @Test
    fun should_Return33point33Percent_When_MedicalAppsAreOneThirdOfAppsList() {
        // given list of apps with 33.33% medical apps
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Medical",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                "1.6 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                51.0, 2000, "8.0 and up"
            )
        )
        appList.add(
            App(
                "Eyes : Nonogram","GAMEFOX", "Puzzle",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                51.0,1500,"5.0 and up")
        )
        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList, "Medical")
        // then check the result
        assertEquals(33.33, percentage)
    }

    @Test
    fun should_ReturnZero_When_NoMedicalAppInTheAppsList() {
        // given list of apps with zero medical apps
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Photography",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                "1.6 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                51.0, 2000, "8.0 and up"
            )
        )
        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList, "Medical")
        // then check the result
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyList() {
        // given empty list
        val appList = mutableListOf<App>()

        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList, "Medical")

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
                "1.6 and up"
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
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "AD Books", "Daovude", "Libraries & Demo",
                LocalDate.parse("January 10 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                30.0, 5, "5.0 and up"
            )
        )
        appList.add(
            App(
                "myAudi", "Audi", "Auto & Vehicles",
                LocalDate.parse("May 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                53.0, 1250, "8.0 and up"
            )
        )
        appList[1]

        // when
        val oldestApp = appAnalyzer.getOldestApp(appList)
        // then check the result
        assertEquals(appList[1], oldestApp)
    }

    @Test
    fun should_ReturnNull_When_HaveEmptyList() {
        // given empty list
        val appList = mutableListOf<App>()
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
                "7 and up"
            )
        )

        //when
        val percentage = appAnalyzer.percentageOfAndroid9AndUp(appList, "9 and up")

        //then
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_Return_33point33_When_ListHaveAppsRequiredAndroid9AndUp() {
        //given
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "9 and up"
            )
        )
        appList.add(
            App(
                "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                98.1, 2000, "8.0 and up"
            )
        )
        //when
        val percentage = appAnalyzer.percentageOfAndroid9AndUp(appList,"9 and up")

        //then
        assertEquals(33.33, percentage)
    }

    @Test
    fun should_ReturnZero_When_ListIsEmpty() {
        //given
        val appList = mutableListOf<App>()

        //when
        val percentage = appAnalyzer.percentageOfAndroid9AndUp(appList, "7 and up")

        //then
        assertEquals(0.0, percentage)
    }

    // endregion

    // region test functions for getLargest10Apps function
    @Test
    fun should_ReturnLargest10Apps_When_TheListOfAppsContainsMoreThan9Apps(){
        // given
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.11, 2000, "8.0 and up"
            )
        )
        appList.add(
            App(
                "Eyes : Nonogram", "GAMEFOX", "Puzzle",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.23, 1500, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Real Drift Car Racing", "Real Games srls", "Racing",
                LocalDate.parse("March 26 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.74, 500, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                2.2, 2500, "7.0 and up"
            )
        )
        appList.add(
            App(
                "Angel Saga: Hero Action RPG", "Alchemist Games Inc.", "Action",
                LocalDate.parse("April 1 2020", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                58.0, 200, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Crazy Pusher", "Borg Studio", "Casino",
                LocalDate.parse("February 2 2019", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                112.8, 3000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                LocalDate.parse("December 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                532.0, 2800, "5.2 and up"
            )
        )
        appList.add(
            App(
                "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                691.3, 2600, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Manta: Comics & Graphic Novels", "RIDI Corporation", "Comics",
                LocalDate.parse("May 16 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1150.2, 2300, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Sago Mini School (Kids 2-5)", "Sago Mini", "Education",
                LocalDate.parse("February 24 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1552.0, 800, "4.4 and up"
            )
        )

        val listOfLargest10Apps = mutableListOf<App>()
        listOfLargest10Apps.add(appList[10])
        listOfLargest10Apps.add(appList[9])
        listOfLargest10Apps.add(appList[8])
        listOfLargest10Apps.add(appList[7])
        listOfLargest10Apps.add(appList[6])
        listOfLargest10Apps.add(appList[5])
        listOfLargest10Apps.add(appList[4])
        listOfLargest10Apps.add(appList[3])
        listOfLargest10Apps.add(appList[2])
        listOfLargest10Apps.add(appList[1])

        // when fined the largest10 app of the list
        val result = appAnalyzer.getLargest10Apps(appList)
        // then
        assertEquals(listOfLargest10Apps, result)
    }

    @Test
    fun should_ReturnEveryLargestApp_When_TheListOfAppsContainsLessThan10Apps() {
        // given list of apps have 5 element
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("923.2K"), 2500, "7.0 and up"
            )
        )
        appList.add(
            App(
                "Crazy Pusher", "Borg Studio", "Casino",
                LocalDate.parse("March 25 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("123.2M"), 3000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                LocalDate.parse("March 9 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("522.0M"), 2800, "5.2 and up"
            )
        )
        appList.add(
            App(
                "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("600.2M"), 2600, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Dinosaur Airport:Game for kids", "Yateland - Learning Games For Kids", "Educational",
                LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("1001.0M"), 4000, "4.1 and up"
            )
        )
        val listOfLargestApps = mutableListOf<App>()
        listOfLargestApps.add(appList[4])
        listOfLargestApps.add(appList[3])
        listOfLargestApps.add(appList[2])
        listOfLargestApps.add(appList[1])
        listOfLargestApps.add(appList[0])
        // when fined the largest10 app of list
        val result = appAnalyzer.getLargest10Apps(appList)
        // then
        assertEquals(listOfLargestApps, result)
    }

    @Test
    fun should_ReturnNull_When_TheListOfAppsIsEmpty() {
        // given empty list of apps
        val appList = mutableListOf<App>()

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
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.11, 2000, "8.0 and up"
            )
        )
        appList.add(
            App(
                "Eyes : Nonogram", "GAMEFOX", "Puzzle",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.23, 3000, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Real Drift Car Racing", "Real Games srls", "Racing",
                LocalDate.parse("March 26 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.74, 4000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                2.2, 5000, "7.0 and up"
            )
        )
        appList.add(
            App(
                "Angel Saga: Hero Action RPG", "Alchemist Games Inc.", "Action",
                LocalDate.parse("April 1 2020", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                58.0, 6000, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Crazy Pusher", "Borg Studio", "Casino",
                LocalDate.parse("February 2 2019", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                112.8, 7000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                LocalDate.parse("December 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                532.0, 8000, "5.2 and up"
            )
        )
        appList.add(
            App(
                "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                691.3, 9000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Manta: Comics & Graphic Novels", "RIDI Corporation", "Comics",
                LocalDate.parse("May 16 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1150.2, 10000, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Sago Mini School (Kids 2-5)", "Sago Mini", "Education",
                LocalDate.parse("February 24 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1552.0, 11000, "4.4 and up"
            )
        )

        val listOfLargest10Apps = mutableListOf<App>()
        listOfLargest10Apps.add(appList[10])
        listOfLargest10Apps.add(appList[9])
        listOfLargest10Apps.add(appList[8])
        listOfLargest10Apps.add(appList[7])
        listOfLargest10Apps.add(appList[6])
        listOfLargest10Apps.add(appList[5])
        listOfLargest10Apps.add(appList[4])
        listOfLargest10Apps.add(appList[3])
        listOfLargest10Apps.add(appList[2])
        listOfLargest10Apps.add(appList[1])

        // when fined the largest10 app of the list
        val result = appAnalyzer.getTop10InstalledApps(appList)
        // then
        assertEquals(listOfLargest10Apps, result)
    }

    @Test
    fun should_ReturnAllElementAppName_When_TheListOfAppsContainsLessThan10Apps() {
        // given list of apps have 5 elements
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                23.2, 1000, "7.0 and up"
            )
        )
        appList.add(
            App(
                "Crazy Pusher", "Borg Studio", "Casino",
                LocalDate.parse("March 25 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1.3, 2000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                LocalDate.parse("March 9 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.52, 3000, "5.2 and up"
            )
        )
        appList.add(
            App(
                "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                52.0, 4000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Dinosaur Airport:Game for kids", "Yateland - Learning Games For Kids", "Educational",
                LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                762.3, 5000, "4.1 and up"
            )
        )
        val listOfLargestApps = mutableListOf<App>()
        listOfLargestApps.add(appList[4])
        listOfLargestApps.add(appList[3])
        listOfLargestApps.add(appList[2])
        listOfLargestApps.add(appList[1])
        listOfLargestApps.add(appList[0])
        // when
        val result = appAnalyzer.getTop10InstalledApps(appList)
        // then
        assertEquals(listOfLargestApps, result)
    }

    @Test
    fun should_Return_Null_When_TheListOfAppsIsEmpty() {
        // given empty list of google play apps
        val appList = mutableListOf<App>()

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
        val appList = mutableListOf<App>()

        appList.add(
            App(
                "Novi", "Meta Platforms Inc", "Finance",
                LocalDate.parse("May 5 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("1K"), 50000, "6.0 and up"
            )
        )
        appList.add(
            App(
                "Facebook Portal", "Meta Platforms Inc", "Lifestyle",
                LocalDate.parse("April 14 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("2K"), 100000, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Messenger Kids – The Messaging App for Kids", "Meta Platforms Inc", "Communication",
                LocalDate.parse("May 17 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("5K"), 10000000, "Varies with device"
            )
        )
        appList.add(
            App(
                "Facebook Lite", "Meta Platforms Inc", "Social",
                LocalDate.parse("May 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("3K"), 1000000000, "Varies with device"
            )
        )
        appList.add(
            App(
                "Messenger Lite", "Meta Platforms Inc", "Communication",
                LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("4K"), 500000000, "4.0 and up"
            )
        )

        val currentApp = App(
            "Messenger Kids – The Messaging App for Kids", "Meta Platforms Inc", "Communication",
            LocalDate.parse("May 17 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
            convertToMegaByte("5K"), 10000000, "Varies with device"
        )

        //when find the largest app
        val result = appAnalyzer.getLargestAppSizeDevelopedByMeta(appList,"Meta Platforms Inc")

        // then
        assertEquals(currentApp, result)
    }

    @Test
    fun should_ReturnNull_MetaApps_When_AppsList_IsEmpty() {
        // given empty list of apps
        val appList = mutableListOf<App>()

        // when find the top installed apps name
        val result = appAnalyzer.getLargestAppSizeDevelopedByMeta(appList,"Meta Platforms Inc")
        // then
        assertNull(result)
    }

    @Test
    fun should_ReturnNull_When_ThereIsNotMetaPlatformApps() {
        // given list has not MetaPlatformApps
        val appList = mutableListOf<App>()

        appList.add(
            App(
                "Novi", "Pangea Money Transfer", "Finance",
                LocalDate.parse("May 5 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("1k"), 50000, "6.0 and up"
            )
        )

        appList.add(
            App(
                "Facebook Portal", "Pangea Money Transfer", "Lifestyle",
                LocalDate.parse("April 14 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("2k"), 100000, "5.0 and up"
            )
        )

        appList.add(
            App(
                "Messenger Kids – The Messaging App for Kids", "Pangea Money Transfer", "Communication",
                LocalDate.parse("May 17 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("5k"), 10000000, "Varies with device"
            )
        )

        appList.add(
            App(
                "Facebook Lite", "Pangea Money Transfer", "Social",
                LocalDate.parse("May 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("3k"), 1000000000, "Varies with device"
            )
        )

        appList.add(
            App(
                "Messenger Lite", "Pangea Money Transfer", "Communication",
                LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                convertToMegaByte("4k"), 500000000, "4.0 and up"
            )
        )

        // when
        val result = appAnalyzer.getLargestAppSizeDevelopedByMeta(appList,"Meta Platforms Inc")
        // then
        assertNull(result)
    }

    //endregion

}