package com.example.jiralogger.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}