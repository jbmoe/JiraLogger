package com.example.jiralogger.data.remote.dto

import com.example.jiralogger.domain.model.Issue
import com.google.gson.annotations.SerializedName


data class ApiResponse(
    val expand: String?,
    val startAt: Int?,
    val maxResults: Int?,
    val total: Int?,
    val issues: List<IssueDto>
) {
    fun toIssuesList(): List<Issue> {
        return issues.map { it.toIssue() }
    }
}

data class IssueDto(
    val id: String,
    val key: String,
    val fields: Fields
) {
    fun toIssue(): Issue {
        return Issue(
            id = id,
            key = key,
            project = fields.project,
            priority = fields.priority,
            status = fields.status,
            issuetype = fields.issuetype,
            summary = fields.summary ?: "",
            description = fields.description ?: ""
        )
    }
}

data class Fields(
    val issuetype: Issuetype,
    val timespent: Int? = null,
    val project: Project,
    val aggregatetimespent: Int? = null,
    val lastViewed: String? = null,
    val watches: Watches? = null,
    val created: String? = null,
    val priority: Priority,
    val labels: List<Any>? = null,
    val timeestimate: Any? = null,
    val aggregatetimeoriginalestimate: Any? = null,
    val versions: List<Any>? = null,
    val issuelinks: List<Any>? = null,
    val assignee: Assignee? = null,
    val updated: String? = null,
    val status: Status,
    val components: List<Any>? = null,
    val timeoriginalestimate: Any? = null,
    val description: String? = "",
    val timetracking: Timetracking? = null,
    val attachment: List<Attachment>? = null,
    val aggregatetimeestimate: Any? = null,
    val summary: String? = "",
    val creator: Creator? = null,
    val subtasks: List<Any>? = null,
    val reporter: Reporter? = null,
    val duedate: Any? = null,
    val progress: Progress? = null,
    val comment: Comment? = null
)

data class Issuetype(
    val id: String, // 3
    val description: String, // A task that needs to be done.
    val iconUrl: String, // https://jira.elbek-vejrup.dk/secure/viewavatar?size=xsmall&avatarId=10318&avatarType=issuetype
    val name: String, // Task
    val subtask: Boolean, // false
)

data class Project(
    val id: String,
    val key: String,
    val name: String,
    val projectTypeKey: String,
    val avatarUrls: AvatarUrls,
    val projectCategory: ProjectCategory
)

data class Watches(
    val self: String?, // https://jira.elbek-vejrup.dk/rest/api/2/issue/DAL-656/watchers
    val watchCount: Int?, // 2
    val isWatching: Boolean? // false
)

data class Priority(
    val iconUrl: String = "", // https://jira.elbek-vejrup.dk/images/icons/priorities/medium.svg
    val name: String = "", // Normal
    val id: String = ""  // 3
)

data class Assignee(
    val self: String?, // https://jira.elbek-vejrup.dk/rest/api/2/user?username=jem
    val name: String?, // jem
    val key: String?, // JIRAUSER25235
    val emailAddress: String?, // jem@elbek-vejrup.dk
    val avatarUrls: AvatarUrls?,
    val displayName: String?, // Jeppe Bach Møller
    val active: Boolean?, // true
    val timeZone: String? // Europe/Paris
)

data class Status(
    val name: String, // Design Approved
    val description: String, // The design has been approved.
    val iconUrl: String, // https://jira.elbek-vejrup.dk/images/icons/statuses/generic.png
    val statusCategory: StatusCategory
)

data class Timetracking(
    val timeSpent: String?, // 0.75h
    val timeSpentSeconds: Int? // 2700
)

data class Attachment(
    val self: String?, // https://jira.elbek-vejrup.dk/rest/api/2/attachment/210442
    val id: String?, // 210442
    val filename: String?, // Dok1.docx
    val author: Author?,
    val created: String?, // 2021-10-08T09:19:01.463+0200
    val size: Int?, // 50244
    val mimeType: String?, // application/vnd.openxmlformats-officedocument.wordprocessingml.document
    val content: String?, // https://jira.elbek-vejrup.dk/secure/attachment/210442/Dok1.docx
    val thumbnail: String? // https://jira.elbek-vejrup.dk/secure/thumbnail/211450/_thumb_211450.png
)

data class Creator(
    val self: String?, // https://jira.elbek-vejrup.dk/rest/api/2/user?username=mira%40autologik.dk
    val name: String?, // mira@autologik.dk
    val key: String?, // JIRAUSER25216
    val emailAddress: String?, // mira@autologik.dk
    val avatarUrls: AvatarUrls?,
    val displayName: String?, // Michael Rasmussen
    val active: Boolean?, // true
    val timeZone: String? // Europe/Paris
)

data class Reporter(
    val self: String?, // https://jira.elbek-vejrup.dk/rest/api/2/user?username=mira%40autologik.dk
    val name: String?, // mira@autologik.dk
    val key: String?, // JIRAUSER25216
    val emailAddress: String?, // mira@autologik.dk
    val avatarUrls: AvatarUrls?,
    val displayName: String?, // Michael Rasmussen
    val active: Boolean?, // true
    val timeZone: String? // Europe/Paris
)

data class Aggregateprogress(
    val progress: Int?, // 2700
    val total: Int?, // 2700
    val percent: Int? // 100
)

data class Progress(
    val progress: Int?, // 2700
    val total: Int?, // 2700
    val percent: Int? // 100
)

data class Comment(
    val comments: List<CommentX>?,
    val maxResults: Int?, // 7
    val total: Int?, // 7
    val startAt: Int? // 0
)

data class AvatarUrls(
    @SerializedName("48x48")
    val x48: String, // https://jira.elbek-vejrup.dk/secure/projectavatar?pid=11045&avatarId=11303
    @SerializedName("24x24")
    val x24: String, // https://jira.elbek-vejrup.dk/secure/projectavatar?size=small&pid=11045&avatarId=11303
    @SerializedName("16x16")
    val x16: String, // https://jira.elbek-vejrup.dk/secure/projectavatar?size=xsmall&pid=11045&avatarId=11303
    @SerializedName("32x32")
    val x32: String  // https://jira.elbek-vejrup.dk/secure/projectavatar?size=medium&pid=11045&avatarId=11303
)

data class ProjectCategory(
    val id: String, // 10118
    val description: String,
    val name: String // 415 - Transport
)

data class StatusCategory(
    val id: Int, // 4
    val key: String, // indeterminate
    val colorName: String, // yellow
    val name: String // In Progress
)

data class Author(
    val self: String?, // https://jira.elbek-vejrup.dk/rest/api/2/user?username=mira%40autologik.dk
    val name: String?, // mira@autologik.dk
    val key: String?, // JIRAUSER25216
    val emailAddress: String?, // mira@autologik.dk
    val avatarUrls: AvatarUrls?,
    val displayName: String?, // Michael Rasmussen
    val active: Boolean?, // true
    val timeZone: String? // Europe/Paris
)

data class CommentX(
    val self: String?, // https://jira.elbek-vejrup.dk/rest/api/2/issue/170514/comment/392868
    val id: String?, // 392868
    val author: Author?,
    val body: String?, // Hej MichaelJeg tænker det er farligt at lave en regel i systemet som gælder alle jeres bookinger. Jeg tænker måske at i per. kunde burde have mulighed for at angive om en booking skal indeholde en "kunderef"Når det så er sagt skal vi også have fundet et tidspunkt i jeres ordre proces hvor vi skal begynde at "tjekke" på om feltet er udfyldt på de udvalgte kunder.Er det når bookingen sættes på et chartek eller når bookingstatus ændres fra en ting til en anden ting? - eller først ved fakturering ?Her tænker jeg at jeres operation må på banen med et input til hvornår det falder bedst i processen 
    val updateAuthor: Author?,
    val created: String?, // 2021-10-13T14:19:10.800+0200
    val updated: String?, // 2021-10-13T14:19:10.800+0200
    val visibility: Visibility?
)

data class Visibility(
    val type: String?, // role
    val value: String? // Consultants
)

