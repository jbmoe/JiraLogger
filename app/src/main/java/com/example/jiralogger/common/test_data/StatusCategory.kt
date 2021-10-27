package com.example.jiralogger.common.test_data

import com.example.jiralogger.data.remote.dto.StatusCategory

object StatusCategory {
    val NOCAT = StatusCategory(
        id = 1,
        key = "undefined",
        colorName = "medium-gray",
        name = "No Category"
    )

    val TODO = StatusCategory(
        id = 2,
        key = "new",
        colorName = "blue-gray",
        name = "To Do"
    )

    val DONE = StatusCategory(
        id = 3,
        key = "done",
        colorName = "green",
        name = "Done"
    )

    val IN_PROGRESS = StatusCategory(
        id = 4,
        key = "indeterminate",
        colorName = "yellow",
        name = "In Progress"
    )
}