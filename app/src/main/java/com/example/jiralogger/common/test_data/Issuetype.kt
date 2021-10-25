package com.example.jiralogger.common.test_data

import com.example.jiralogger.data.remote.dto.Issuetype


object Issuetype {
    val TASK = Issuetype(
        id = "3",
        description = "A task that needs to be done.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype",
        name = "Task",
        subtask = false,
    )

    val SUB_TASK = Issuetype(
        id = "5",
        description = "A sub-task that needs to be done.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10316&avatarType=issuetype",
        name = "Sub-Task",
        subtask = true,
    )

    val CHANGE_REQUEST = Issuetype(
        id = "10200",
        description = "A change request that need to be done.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10304&avatarType=issuetype",
        name = "Change Request",
        subtask = false,
    )

    val CHANGE_REQUEST_SUB_TASK = Issuetype(
        id = "11100",
        description = "A change request sub-task that needs to be done.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10316&avatarType=issuetype",
        name = "Change Request Sub-Task",
        subtask = true,
    )

    val CUSTOMER_TASK = Issuetype(
        id = "10704",
        description = "A task handled by the customer.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10306&avatarType=issuetype",
        name = "Customer Task",
        subtask = false
    )

    val SUPPORT = Issuetype(
        id = "10600",
        description = "For general IT problems and questions.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10320&avatarType=issuetype",
        name = "Support",
        subtask = false
    )

    val BUG = Issuetype(
        id = "11000",
        description = "A problem which impairs or prevents the functions of the product.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10303&avatarType=issuetype",
        name = "Bug",
        subtask = false
    )

    val EPIC = Issuetype(
        id = "10401",
        description = "Created by Jira Software - do not edit or delete. Issue type for a big user story that needs to be broken down.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10307&avatarType=issuetype",
        name = "Epic",
        subtask = false
    )

    val IMPROVEMENT = Issuetype(
        id = "11001",
        description = "An enhancement to an existing feature.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10310&avatarType=issuetype",
        name = "Improvement",
        subtask = false
    )
    val NEW_FEATURE = Issuetype(
        id = "11002",
        description = "A new feature of the product.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10311&avatarType=issuetype",
        name = "New Feature ",
        subtask = false
    )
    val STORY = Issuetype(
        id = "10400",
        description = "Created by Jira Software - do not edit or delete. Issue type for a user story.",
        iconUrl = "https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10315&avatarType=issuetype",
        name = "Story",
        subtask = false
    )
}