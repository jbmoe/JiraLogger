package com.example.jiralogger.common.test_data

import com.example.jiralogger.data.remote.dto.AvatarUrls
import com.example.jiralogger.data.remote.dto.Project

object Project {
    val KOL = Project(
        id = "15933",
        key = "KOL",
        name = "Kolding Havn",
        projectTypeKey = "software",
        avatarUrls = AvatarUrls(
            x48 = "https://jira.elbek-vejrup.dk/secure/projectavatar?pid=15933&avatarId=15007",
            x24 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=small&pid=15933&avatarId=15007",
            x16 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=xsmall&pid=15933&avatarId=15007",
            x32 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=medium&pid=15933&avatarId=15007"
        ),
        projectCategory = ProjectCategory.PROJCAT410
    )

    val ENNOVA = Project(
        id = "11315",
        key = "ENNOVA",
        name = "Ennova",
        projectTypeKey = "software",
        avatarUrls = AvatarUrls(
            x48 = "https://jira.elbek-vejrup.dk/secure/projectavatar?pid=11315&avatarId=10011",
            x24 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=small&pid=11315&avatarId=10011",
            x16 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=xsmall&pid=11315&avatarId=10011",
            x32 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=medium&pid=11315&avatarId=10011"
        ),
        projectCategory = ProjectCategory.PROJCAT410
    )

    val ESBJ = Project(
        id = "15928",
        key = "ESBJ",
        name = "Esbjerg Havn",
        projectTypeKey = "software",
        avatarUrls = AvatarUrls(
            x48 = "https://jira.elbek-vejrup.dk/secure/projectavatar?pid=15928&avatarId=15011",
            x24 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=small&pid=15928&avatarId=15011",
            x16 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=xsmall&pid=15928&avatarId=15011",
            x32 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=medium&pid=15928&avatarId=15011"
        ),
        projectCategory = ProjectCategory.PROJCAT410
    )

    val QQ = Project(
        id = "22102",
        key = "QQ",
        name = "QuineQuintax ApS",
        projectTypeKey = "software",
        avatarUrls = AvatarUrls(
            x48 = "https://jira.elbek-vejrup.dk/secure/projectavatar?avatarId=12103",
            x24 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=small&avatarId=12103",
            x16 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=xsmall&avatarId=12103",
            x32 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=medium&avatarId=12103"
        ),
        projectCategory = ProjectCategory.PROJCAT410
    )

    val WHT = Project(
        id = "11048",
        key = "WHT",
        name = "WHT",
        projectTypeKey = "software",
        avatarUrls = AvatarUrls(
            x48 = "https://jira.elbek-vejrup.dk/secure/projectavatar?pid=11048&avatarId=11317",
            x24 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=small&pid=11048&avatarId=11317",
            x16 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=xsmall&pid=11048&avatarId=11317",
            x32 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=medium&pid=11048&avatarId=11317"
        ),
        projectCategory = ProjectCategory.PROJCAT410
    )

    val DAL = Project(
        id = "11045",
        key = "DAL",
        name = "Dansk Auto Logik",
        projectTypeKey = "software",
        avatarUrls = AvatarUrls(
            x48 = "https://jira.elbek-vejrup.dk/secure/projectavatar?pid=11045&avatarId=11303",
            x24 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=small&pid=11045&avatarId=11303",
            x16 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=xsmall&pid=11045&avatarId=11303",
            x32 = "https://jira.elbek-vejrup.dk/secure/projectavatar?size=medium&pid=11045&avatarId=11303"
        ),
        projectCategory = ProjectCategory.PROJCAT410
    )
}