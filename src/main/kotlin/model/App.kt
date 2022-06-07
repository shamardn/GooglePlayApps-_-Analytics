package model

import java.time.LocalDate

data class App(
    val appName: String,
    val company: String,
    val category: String,
    val updated: LocalDate,
    val size: Double,
    val installs: Long,
    val requiresAndroid: Double
) {
}