package com.example.jiralogger.common.test_data

import com.example.jiralogger.data.remote.dto.Priority

object Priority {
    val HIGH = Priority(
        id = "2",
        name = "High",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/priorities/major.svg"
    )

    val NORMAL = Priority(
        id = "3",
        name = "Normal",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/priorities/medium.svg"
    )

    val LOW = Priority(
        id = "4",
        name = "Low",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/priorities/minor.svg"
    )

    val CRITICAL = Priority(
        id = "10001",
        name = "Critical",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/priorities/critical.svg"
    )

    val BLOCKER = Priority(
        id = "10000",
        name = "Blocker",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/priorities/blocker.svg"
    )
}