package com.example.jiralogger.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToTime(time: Long, pattern: String): String {
    val date = Date(time)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}

fun getFormattedTime(min: Int): String {
    var toReturn = ""
    var hours = min / 60
    var minutes = min % 60

    if (hours != 0) {
        toReturn += "${hours}h "
    }
    if (minutes != 0) {
        toReturn += "${minutes}m"
    }

    return toReturn
}