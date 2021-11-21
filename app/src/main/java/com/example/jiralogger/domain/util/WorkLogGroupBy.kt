package com.example.jiralogger.domain.util

sealed class WorkLogGroupBy(val orderType: OrderType, override val name: String) : HasName {
    class Issue(orderType: OrderType) : WorkLogGroupBy(orderType = orderType, name = "Issue")
    class Date(orderType: OrderType) : WorkLogGroupBy(orderType = orderType, name = "Date")

    fun copy(orderType: OrderType): WorkLogGroupBy {
        return when (this) {
            is Date -> Date(orderType)
            is Issue -> Issue(orderType)
        }
    }

    override fun equals(other: Any?): Boolean {
        return name == (other as WorkLogGroupBy).name
    }
}