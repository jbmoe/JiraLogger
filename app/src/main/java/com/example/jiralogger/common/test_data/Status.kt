package com.example.jiralogger.common.test_data

import com.example.jiralogger.common.test_data.StatusCategory.DONE
import com.example.jiralogger.common.test_data.StatusCategory.IN_PROGRESS
import com.example.jiralogger.common.test_data.StatusCategory.TODO
import com.example.jiralogger.data.remote.dto.Status

object Status {
    val OPEN = Status(
        name = "Open",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/open.png",
        description = "The issue is open and awaiting authorisation.",
        statusCategory = TODO
    )

    val AUTHORIZED = Status(
        name = "Authorized",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/generic.png",
        description = "The issue is authorized and ready for the assignee to start work on it.",
        statusCategory = TODO
    )


    val CLOSED = Status(
        name = "Closed",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/closed.png",
        description = "The issue is being quality assured by the assignee.",
        statusCategory = DONE
    )

    val PAUSED = Status(
        name = "Paused",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/generic.png",
        description = "The issue is temporarily paused.",
        statusCategory = TODO
    )


    val REVIEW = Status(
        name = "Review",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/generic.png",
        description = "The issue is being reviewed.",
        statusCategory = IN_PROGRESS
    )

    val APPROVED = Status(
        name = "Approved",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/generic.png",
        description = "The issue is approved.",
        statusCategory = IN_PROGRESS
    )

    val REJECTED = Status(
        name = "Rejected",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/generic.png",
        description = "he issue could not be approved.",
        statusCategory = IN_PROGRESS
    )

    val REOPENED = Status(
        name = "Reopened",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/reopened.png",
        description = "This issue was once resolved, but the issue is now reopened and awaiting authorisation.",
        statusCategory = TODO
    )

    val RESOLVED = Status(
        name = "Resolved",
        iconUrl = "https://jira.elbek-vejrup.dk/images/icons/statuses/resolved.png",
        description = "A resolution has been taken, and it is awaiting verification.",
        statusCategory = DONE
    )
}

