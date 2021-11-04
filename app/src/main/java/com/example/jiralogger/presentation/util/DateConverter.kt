package com.example.jiralogger.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToTime(time: Long, pattern: String): String {
    val date = Date(time)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}