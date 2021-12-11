package com.example.jiralogger.common.test_data

import com.example.jiralogger.data.remote.dto.ApiResponse
import com.example.jiralogger.data.remote.dto.Fields
import com.example.jiralogger.data.remote.dto.IssueDto
import com.thedeanda.lorem.LoremIpsum

object Generator {
    private val lorem = LoremIpsum.getInstance()

    fun createTestApiResponse(noOfIssues: Int): ApiResponse {
        val issueDtos: MutableList<IssueDto> = mutableListOf()
        for (i in 0..noOfIssues) {
            val project = getRandomProject()
            issueDtos.add(
                IssueDto(
                    id = i.toString(),
                    key = getRandomKey(project),
                    fields = Fields(
                        summary = lorem.getTitle(2, 10),
                        description = lorem.getParagraphs(0, 6),
                        project = project,
                        priority = getRandomPriority(),
                        status = getRandomStatus(),
                        issuetype = getRandomIssueType()
                    )
                )
            )
        }
        return ApiResponse(
            expand = "schemas,names",
            startAt = 0,
            maxResults = 0,
            total = 62053,
            issues = issueDtos
        )
    }

    private fun getRandomKey(project: com.example.jiralogger.data.remote.dto.Project): String =
        "${project.key}-${String.format("%03d", (1..999).random())}"

    private fun getRandomProject(): com.example.jiralogger.data.remote.dto.Project {
        val x: Int = (0..5).random()
        return when (x) {
            0 -> Project.DAL
            1 -> Project.ESBJ
            2 -> Project.ENNOVA
            3 -> Project.KOL
            4 -> Project.QQ
            else -> Project.WHT
        }
    }

    private fun getRandomPriority(): com.example.jiralogger.data.remote.dto.Priority {
        return when ((0..5).random()) {
            0 -> Priority.LOW
            1 -> Priority.NORMAL
            2 -> Priority.HIGH
            3 -> Priority.BLOCKER
            else -> Priority.CRITICAL
        }
    }

    private fun getRandomStatus(): com.example.jiralogger.data.remote.dto.Status {
        return when ((0..8).random()) {
            0 -> Status.OPEN
            1 -> Status.AUTHORIZED
            2 -> Status.CLOSED
            3 -> Status.PAUSED
            4 -> Status.REVIEW
            5 -> Status.APPROVED
            6 -> Status.REJECTED
            7 -> Status.REOPENED
            else -> Status.RESOLVED
        }
    }

    private fun getRandomIssueType(): com.example.jiralogger.data.remote.dto.Issuetype {
        return when ((0..10).random()) {
            0 -> Issuetype.TASK
            1 -> Issuetype.SUB_TASK
            2 -> Issuetype.CHANGE_REQUEST
            3 -> Issuetype.CHANGE_REQUEST_SUB_TASK
            4 -> Issuetype.CUSTOMER_TASK
            5 -> Issuetype.SUPPORT
            6 -> Issuetype.BUG
            7 -> Issuetype.EPIC
            8 -> Issuetype.IMPROVEMENT
            9 -> Issuetype.NEW_FEATURE
            else -> Issuetype.STORY
        }
    }
}