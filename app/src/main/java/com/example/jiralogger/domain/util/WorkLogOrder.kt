package com.example.jiralogger.domain.util

sealed class WorkLogOrder(val orderType: OrderType, val name: String) {
    class Date(orderType: OrderType) : WorkLogOrder(orderType = orderType, name = "Date")
    class Issue(orderType: OrderType) : WorkLogOrder(orderType = orderType, name = "Issue")

    fun copy(orderType: OrderType): WorkLogOrder {
        return when (this) {
            is Date -> Date(orderType)
            is Issue -> Issue(orderType)
        }
    }
}