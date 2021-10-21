package com.example.jiralogger.common

import com.example.jiralogger.data.remote.dto.ApiResult
import com.example.jiralogger.data.remote.dto.Fields
import com.example.jiralogger.data.remote.dto.IssueDto
import com.example.jiralogger.data.remote.dto.Project
import com.example.jiralogger.domain.model.Issue

object TestData {
    val ISSUE_TEST_OBJECT = Issue(
        projectName = "Dansk Auto Logik",
        projectImageUrl = "https://jira.elbek-vejrup.dk/secure/projectavatar?pid=11045&avatarId=11303",
        description = "Der er efterhånden en lang række kunder som kræver at feltet ”Kunde Ref.” er udfyldt på fakturaer.\n" +
                "\n" +
                "Når ”Kunde Ref.” ikke er udfyldt skaber det en del ekstra arbejde med henvendelser fra kunderne der ikke vil betale før de har oplysningerne.\n" +
                "\n" +
                "Derfor er ønsket at feltet kræves udfyldt ved oprettelse af en booking på billedet \"Bookinger ej klar\". (se vedhæftet).\n" +
                "\n" +
                "Vi har en række indlæsninger fra fil - jeg har pt. ikke overblik over hvordan det eventuelt skal løses.",
        summary = "Tvungen udfyldelse af \"Kunde Ref.\"",
        key = "DAL-656",
        id = "1"
    )

    val API_RESULT_TEST_OBJECT = ApiResult(
        expand = "schemas,names",
        startAt = 0,
        maxResults = 0,
        total = 62053,
        issues = listOf(
            IssueDto(
                id = "1",
                key = "DAL-656",
                fields = Fields(
                    summary = "Tvungen udfyldelse af \"Kunde Ref.\"",
                    description = "Der er efterhånden en lang række kunder som kræver at feltet ”Kunde Ref.” er udfyldt på fakturaer.\n" +
                            "\n" +
                            "Når ”Kunde Ref.” ikke er udfyldt skaber det en del ekstra arbejde med henvendelser fra kunderne der ikke vil betale før de har oplysningerne.\n" +
                            "\n" +
                            "Derfor er ønsket at feltet kræves udfyldt ved oprettelse af en booking på billedet \"Bookinger ej klar\". (se vedhæftet).\n" +
                            "\n" +
                            "Vi har en række indlæsninger fra fil - jeg har pt. ikke overblik over hvordan det eventuelt skal løses.",
                    project = Project(name = "Dansk Auto Logik")
                )
            ), IssueDto(
                id = "2",
                key = "ENNOVA-57",
                fields = Fields(
                    summary = "API til at se om en faktura er betalt",
                    description = "From: Allan Jensen <aj@ennova.com>\n" +
                            "Sent: 20. september 2021 14:40\n" +
                            "To: Diana Bøgballe <DIB@elbek-vejrup.dk>\n" +
                            "Subject: RE: Nye api / pages\n" +
                            "\n" +
                            "Hej Diana\n" +
                            "\n" +
                            "Pas. Det ved jeg ikke. Jeg har bare hentet nedenstående SQL, som værdien open er true/false alt afhængig af om fakturaen er betalt. Hvordan NAV håndtere restbeløb ved jeg ikke. Jeg ved kun den holder true/false værdien i ”OPEN” kolonnen\n" +
                            "\n" +
                            "select [Document No_], [Open] from dbo.[Ennova A_S\$Cust_ Ledger Entry]\n" +
                            "\n" +
                            "Med venlig hilsen\n" +
                            "\n" +
                            "Allan Jensen,\n" +
                            "Senior System Developer\n" +
                            "M: +45 22 77 73 22 | E: aj@ennova.com\n" +
                            "\n" +
                            "Website | Newsletter",
                    project = Project(name = "Ennova")
                )
            ), IssueDto(
                id = "3",
                key = "ESBJ-237",
                fields = Fields(
                    summary = "Kranstyring - liste skal vises per dag",
                    description = "Hej Flemming\n" +
                            "\n" +
                            "Vi kan ikke bruge farver til at adskille med da vi allerede formatere i 3 forskellige farver ud fra status på kranbookinglinjen.\n" +
                            "\n" +
                            "Vi har et andet sted i Dynamics 365 Business Central hvor man gør det på nedenstående måde. Var det en ide?\n" +
                            "\n" +
                            "Vi har lige brugt en halv time på om vi ”hurtigt” kunne få det til at virke hos jer men vi kan se at vi nok skal bruge 3 – 3,5 time før vi kan komme i mål med det.\n" +
                            "\n" +
                            "Lad mig høre hvad i tænker\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "Venlig hilsen\n" +
                            "\n" +
                            "Ole Palner\n" +
                            "Seniorkonsulent\n" +
                            "\n" +
                            "*Elbek & Vejrup A/S\n" +
                            "* Tangen 6 • 8200 Aarhus N\n" +
                            "Tlf.: 7020 2086 • elbek-vejrup.dk\n" +
                            "\n" +
                            "Se hvordan vi kan skabe forretningsmæssig værdi:\n" +
                            "\n" +
                            "* \n" +
                            "*\n" +
                            "\n" +
                            "From: Flemming Aarhus Pedersen, Esbjerg Havn <FLP@portesbjerg.dk>\n" +
                            "\n" +
                            "Sent: 2. juli 2021 11:24\n" +
                            "\n" +
                            "To: Ole Palner <OP@elbek-vejrup.dk>\n" +
                            "\n" +
                            "Subject: SV: Kranstyring\n" +
                            "\n" +
                            "Hej Ole\n" +
                            "\n" +
                            "Ja – det er denne oversigt – hvor dag for dag skal adskilles med minimum to farver (som kendes fra andre lister) og/eller en skille linje så det er let at se adskillelse dag for dag.\n" +
                            "\n" +
                            "Med venlig hilsen / Best regards\n" +
                            "Flemming Aarhus Pedersen\n" +
                            "Økonomidirektør / CFO\n" +
                            "\n" +
                            "\t\n" +
                            "+45 2999 2988 (direct)\tHulvejen 1\n" +
                            "+45 7612 4000(main)\tDK-6700 Esbjerg\n" +
                            "flp@portesbjerg.dk\twww.portesbjerg.dk\n" +
                            "\t\n" +
                            "Fra: Ole Palner <OP@elbek-vejrup.dk>\n" +
                            "\n" +
                            "Sendt: 2. juli 2021 10:56\n" +
                            "\n" +
                            "Til: Flemming Aarhus Pedersen, Esbjerg Havn <FLP@portesbjerg.dk>\n" +
                            "\n" +
                            "Emne: RE: Kranstyring\n" +
                            "\n" +
                            "OK\n" +
                            "\n" +
                            "For lige at være helt sikker på hvilken oversigt du mener har jeg lige vedhæftet nedenstående.\n" +
                            "\n" +
                            "Jeg regner med at det er den oversigt du snakker om. Korrekt?\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "Venlig hilsen\n" +
                            "\n" +
                            "Ole Palner\n" +
                            "Seniorkonsulent\n" +
                            "\n" +
                            "*Elbek & Vejrup A/S\n" +
                            "* Tangen 6 • 8200 Aarhus N\n" +
                            "Tlf.: 7020 2086 • elbek-vejrup.dk\n" +
                            "\n" +
                            "Se hvordan vi kan skabe forretningsmæssig værdi:\n" +
                            "\n" +
                            "* \n" +
                            "*\n" +
                            "\n" +
                            "From: Flemming Aarhus Pedersen, Esbjerg Havn <FLP@portesbjerg.dk>\n" +
                            "\n" +
                            "Sent: 2. juli 2021 10:50\n" +
                            "\n" +
                            "To: Ole Palner <OP@elbek-vejrup.dk>\n" +
                            "\n" +
                            "Subject: Kranstyring\n" +
                            "\n" +
                            "Hej Ole\n" +
                            "\n" +
                            "Vi har internt haft en kort snak omkring tilføjelse af kranfører i bookingoversigten.\n" +
                            "\n" +
                            "Men vi bliver nød til at have et lidt længere snak – før jeg kan vende tilbage med den løsning vi tror på.\n" +
                            "\n" +
                            "Men her og nu har jeg et spørgsmål – og som jeg tidligere tror jeg har spurgt til. Er det muligt, i kranbookingoversigten, enten at få forskellige farver pr dag – alternativ en skille linje, så det er tydeligt at se dag for dag på den lange liste.\n" +
                            "\n" +
                            "Go’ weekend.\n" +
                            "\n" +
                            "Med venlig hilsen / Best regards\n" +
                            "Flemming Aarhus Pedersen\n" +
                            "Økonomidirektør / CFO\n" +
                            "\n" +
                            "\t\n" +
                            "+45 2999 2988 (direct)\tHulvejen 1\n" +
                            "+45 7612 4000(main)\tDK-6700 Esbjerg\n" +
                            "flp@portesbjerg.dk\twww.portesbjerg.dk\n" +
                            "\t\t2021-07-02_1403_OP_AT_elbek-vejrup.dk.msg\n",
                    project = Project(name = "Esbjerg Havn")
                )
            ), IssueDto(
                id = "4",
                key = "ENNOVA-46",
                fields = Fields(
                    summary = "Opsæt layout",
                    description = "Layout til dokumenter genopsættes via Microsoft Word, så der udnyttes ny standard\n" +
                            "funktionalitet til dokumenter. Layout skal indeholde tilsvarende oplysninger, men\n" +
                            "udseendet vil være baseret på nyt standard layout fra Microsoft.\n" +
                            "• Faktura\n" +
                            "• Kreditnota\n" +
                            "• Ordrebekræftelse Bruges ikke",
                    project = Project(name = "Ennova")
                )
            ), IssueDto(
                id = "5",
                key = "QQ-10",
                fields = Fields(
                    summary = "Totalsalg 2020/2021 pr varegruppe",
                    description = "hermed listen over de varegrupper hvor jeg ønsker total salg for 2020 og 2021 indtil i dag.\n" +
                            "\n" +
                            "SIKKERHEDSUDSTYR 10010001 til og med 10110911\n" +
                            "\n" +
                            "INSEKFTANGER 270001 - 270020\n" +
                            "\n" +
                            "BADEVENTILATION 300021 - 300112\n" +
                            "\n" +
                            "VARME 33105 - 350038\n" +
                            "\n" +
                            "VENTILATOR 350102 - 350127\n" +
                            "\n" +
                            "TERRASSEVARMER 350132 - 350204\n" +
                            "\n" +
                            "STØVLER 38000000 - 38007046\n" +
                            "\n" +
                            "STOPHANER 446065 - 446250\n" +
                            "\n" +
                            "LED STRIPS 500000 - 500018\n" +
                            "\n" +
                            "SAMLEMUFFER + KABELBØJLER 530001 - 530020\n" +
                            "\n" +
                            "CEE STIK 540003 - 540017\n" +
                            "\n" +
                            "MULTISTIK 540021 - 31\n" +
                            "\n" +
                            "ARMATUR 540042 - 540084\n" +
                            "\n" +
                            "STIKDÅSE 540102 - 540152\n" +
                            "\n" +
                            "LAMPER 540202 - 540726\n" +
                            "\n" +
                            "KABELTROMLER + INSTALLATIONS 545003 - 589339\n" +
                            "\n" +
                            "BRÆNDE 75107600 - 75700675\n" +
                            "\n" +
                            "PUMPER 76500100 - 76601013 + 76800012\n" +
                            "\n" +
                            "HAVEMASKINER 76700504 - 76701043\n" +
                            "\n" +
                            "SVEJSETRÅD 77100123 - 77100143\n" +
                            "\n" +
                            "BATTERILADER 78000005 - 78000022\n" +
                            "\n" +
                            "MALERPISTOL + TILBEHØR 80000600 - 80006012\n" +
                            "\n" +
                            "SLANGETROMLER 80006020 - 80006028\n" +
                            "\n" +
                            "KOBLINGER 81000182 - 93001671\n" +
                            "\n" +
                            "KOMPRESSOR 85000001 - 85203026\n" +
                            "\n" +
                            " \n" +
                            "\n" +
                            "intervallet, der gælder er fra det første vare til og med det sidste.",
                    project = Project(name = "QuineQuintax")
                )
            )
        )
    )
}