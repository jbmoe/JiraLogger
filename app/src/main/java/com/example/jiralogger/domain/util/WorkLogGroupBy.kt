package com.example.jiralogger.domain.util

sealed class WorkLogGroupBy(val orderType: OrderType, val name: String) {
    object Issue : WorkLogGroupBy(orderType = OrderType.Ascending, name = "Issue")
    object Date : WorkLogGroupBy(orderType = OrderType.Ascending, name = "Date")

//    fun copy(orderType: OrderType): WorkLogGroupBy {
//        return when (this) {
//            is Date -> Date(orderType)
//            is Issue -> Issue(orderType)
//        }
//    }
}