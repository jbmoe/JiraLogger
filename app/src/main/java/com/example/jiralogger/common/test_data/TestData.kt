package com.example.jiralogger.common.test_data

import com.example.jiralogger.data.remote.dto.ApiResponse
import com.example.jiralogger.data.remote.dto.Fields
import com.example.jiralogger.data.remote.dto.IssueDto
import com.example.jiralogger.domain.model.WorkLog

object TestData {
    private const val ONEDAY = 86400000L
    val WORK_LOG_TEST_DATA = mutableListOf(
        WorkLog(
            id = 1,
            issueId = "DAL-656",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY),
            comment = "Working on issue DAL-656"
        ),
        WorkLog(
            id = 2,
            issueId = "ENNOVA-57",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*2),
            comment = "Working on issue ENNOVA-57"
        ),
        WorkLog(
            id = 3,
            issueId = "ESBJ-237",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*3),
            comment = "Working on issue ESBJ-237"
        ),
        WorkLog(
            id = 4,
            issueId = "ENNOVA-46",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*4),
            comment = "Working on issue ENNOVA-46"
        ),
        WorkLog(
            id = 5,
            issueId = "QQ-10",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*5),
            comment = "Working on issue QQ-10"
        ),
        WorkLog(
            id = 6,
            issueId = "WHT-374",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*6),
            comment = "Working on issue WHT-374"
        ),
        WorkLog(
            id = 7,
            issueId = "ESBJ-248",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*7),
            comment = "Working on issue ESBJ-248"
        ),
        WorkLog(
            id = 8,
            issueId = "ESBJ-243",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*8),
            comment = "Working on issue ESBJ-243"
        ),
        WorkLog(
            id = 9,
            issueId = "ESBJ-233",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*9),
            comment = "Working on issue ESBJ-233"
        ),
        WorkLog(
            id = 10,
            issueId = "KOL-41",
            userId = "JIRAUSER25235",
            timeSpent = "1h 30m",
            timeSpentSeconds = 5400,
            dateWorked = System.currentTimeMillis().minus(ONEDAY*10),
            comment = "Working on issue KOL-41"
        )
    )

    val API_RESULT_TEST_OBJECT = ApiResponse(
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
                    description = "Der er efterh??nden en lang r??kke kunder som kr??ver at feltet ???Kunde Ref.??? er udfyldt p?? fakturaer.\n" +
                            "\n" +
                            "N??r ???Kunde Ref.??? ikke er udfyldt skaber det en del ekstra arbejde med henvendelser fra kunderne der ikke vil betale f??r de har oplysningerne.\n" +
                            "\n" +
                            "Derfor er ??nsket at feltet kr??ves udfyldt ved oprettelse af en booking p?? billedet \"Bookinger ej klar\". (se vedh??ftet).\n" +
                            "\n" +
                            "Vi har en r??kke indl??sninger fra fil - jeg har pt. ikke overblik over hvordan det eventuelt skal l??ses.",
                    project = Project.DAL,
                    priority = Priority.CRITICAL,
                    status = Status.APPROVED,
                    issuetype = Issuetype.BUG
                )
            ),
            IssueDto(
                id = "2",
                key = "ENNOVA-57",
                fields = Fields(
                    summary = "API til at se om en faktura er betalt",
                    description = "From: Allan Jensen <aj@ennova.com>\n" +
                            "Sent: 20. september 2021 14:40\n" +
                            "To: Diana B??gballe <DIB@elbek-vejrup.dk>\n" +
                            "Subject: RE: Nye api / pages\n" +
                            "\n" +
                            "Hej Diana\n" +
                            "\n" +
                            "Pas. Det ved jeg ikke. Jeg har bare hentet nedenst??ende SQL, som v??rdien open er true/false alt afh??ngig af om fakturaen er betalt. Hvordan NAV h??ndtere restbel??b ved jeg ikke. Jeg ved kun den holder true/false v??rdien i ???OPEN??? kolonnen\n" +
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
                    project = Project.ENNOVA,
                    priority = Priority.BLOCKER,
                    status = Status.AUTHORIZED,
                    issuetype = Issuetype.CHANGE_REQUEST
                )
            ),
            IssueDto(
                id = "3",
                key = "ESBJ-237",
                fields = Fields(
                    summary = "Kranstyring - liste skal vises per dag",
                    description = "Hej Flemming\n" +
                            "\n" +
                            "Vi kan ikke bruge farver til at adskille med da vi allerede formatere i 3 forskellige farver ud fra status p?? kranbookinglinjen.\n" +
                            "\n" +
                            "Vi har et andet sted i Dynamics 365 Business Central hvor man g??r det p?? nedenst??ende m??de. Var det en ide?\n" +
                            "\n" +
                            "Vi har lige brugt en halv time p?? om vi ???hurtigt??? kunne f?? det til at virke hos jer men vi kan se at vi nok skal bruge 3 ??? 3,5 time f??r vi kan komme i m??l med det.\n" +
                            "\n" +
                            "Lad mig h??re hvad i t??nker\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "Venlig hilsen\n" +
                            "\n" +
                            "Ole Palner\n" +
                            "Seniorkonsulent\n" +
                            "\n" +
                            "*Elbek & Vejrup A/S\n" +
                            "* Tangen 6 ??? 8200 Aarhus N\n" +
                            "Tlf.: 7020 2086 ??? elbek-vejrup.dk\n" +
                            "\n" +
                            "Se hvordan vi kan skabe forretningsm??ssig v??rdi:\n" +
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
                            "Ja ??? det er denne oversigt ??? hvor dag for dag skal adskilles med minimum to farver (som kendes fra andre lister) og/eller en skille linje s?? det er let at se adskillelse dag for dag.\n" +
                            "\n" +
                            "Med venlig hilsen / Best regards\n" +
                            "Flemming Aarhus Pedersen\n" +
                            "??konomidirekt??r / CFO\n" +
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
                            "For lige at v??re helt sikker p?? hvilken oversigt du mener har jeg lige vedh??ftet nedenst??ende.\n" +
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
                            "* Tangen 6 ??? 8200 Aarhus N\n" +
                            "Tlf.: 7020 2086 ??? elbek-vejrup.dk\n" +
                            "\n" +
                            "Se hvordan vi kan skabe forretningsm??ssig v??rdi:\n" +
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
                            "Vi har internt haft en kort snak omkring tilf??jelse af kranf??rer i bookingoversigten.\n" +
                            "\n" +
                            "Men vi bliver n??d til at have et lidt l??ngere snak ??? f??r jeg kan vende tilbage med den l??sning vi tror p??.\n" +
                            "\n" +
                            "Men her og nu har jeg et sp??rgsm??l ??? og som jeg tidligere tror jeg har spurgt til. Er det muligt, i kranbookingoversigten, enten at f?? forskellige farver pr dag ??? alternativ en skille linje, s?? det er tydeligt at se dag for dag p?? den lange liste.\n" +
                            "\n" +
                            "Go??? weekend.\n" +
                            "\n" +
                            "Med venlig hilsen / Best regards\n" +
                            "Flemming Aarhus Pedersen\n" +
                            "??konomidirekt??r / CFO\n" +
                            "\n" +
                            "\t\n" +
                            "+45 2999 2988 (direct)\tHulvejen 1\n" +
                            "+45 7612 4000(main)\tDK-6700 Esbjerg\n" +
                            "flp@portesbjerg.dk\twww.portesbjerg.dk\n" +
                            "\t\t2021-07-02_1403_OP_AT_elbek-vejrup.dk.msg\n",
                    project = Project.ESBJ,
                    priority = Priority.HIGH,
                    status = Status.CLOSED,
                    issuetype = Issuetype.CHANGE_REQUEST_SUB_TASK
                )
            ),
            IssueDto(
                id = "4",
                key = "ENNOVA-46",
                fields = Fields(
                    summary = "Ops??t layout",
                    description = "Layout til dokumenter genops??ttes via Microsoft Word, s?? der udnyttes ny standard\n" +
                            "funktionalitet til dokumenter. Layout skal indeholde tilsvarende oplysninger, men\n" +
                            "udseendet vil v??re baseret p?? nyt standard layout fra Microsoft.\n" +
                            "??? Faktura\n" +
                            "??? Kreditnota\n" +
                            "??? Ordrebekr??ftelse Bruges ikke",
                    project = Project.ENNOVA,
                    priority = Priority.NORMAL,
                    status = Status.OPEN,
                    issuetype = Issuetype.CUSTOMER_TASK
                )
            ),
            IssueDto(
                id = "5",
                key = "QQ-10",
                fields = Fields(
                    summary = "Totalsalg 2020/2021 pr varegruppe",
                    description = "hermed listen over de varegrupper hvor jeg ??nsker total salg for 2020 og 2021 indtil i dag.\n" +
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
                            "ST??VLER 38000000 - 38007046\n" +
                            "\n" +
                            "STOPHANER 446065 - 446250\n" +
                            "\n" +
                            "LED STRIPS 500000 - 500018\n" +
                            "\n" +
                            "SAMLEMUFFER + KABELB??JLER 530001 - 530020\n" +
                            "\n" +
                            "CEE STIK 540003 - 540017\n" +
                            "\n" +
                            "MULTISTIK 540021 - 31\n" +
                            "\n" +
                            "ARMATUR 540042 - 540084\n" +
                            "\n" +
                            "STIKD??SE 540102 - 540152\n" +
                            "\n" +
                            "LAMPER 540202 - 540726\n" +
                            "\n" +
                            "KABELTROMLER + INSTALLATIONS 545003 - 589339\n" +
                            "\n" +
                            "BR??NDE 75107600 - 75700675\n" +
                            "\n" +
                            "PUMPER 76500100 - 76601013 + 76800012\n" +
                            "\n" +
                            "HAVEMASKINER 76700504 - 76701043\n" +
                            "\n" +
                            "SVEJSETR??D 77100123 - 77100143\n" +
                            "\n" +
                            "BATTERILADER 78000005 - 78000022\n" +
                            "\n" +
                            "MALERPISTOL + TILBEH??R 80000600 - 80006012\n" +
                            "\n" +
                            "SLANGETROMLER 80006020 - 80006028\n" +
                            "\n" +
                            "KOBLINGER 81000182 - 93001671\n" +
                            "\n" +
                            "KOMPRESSOR 85000001 - 85203026\n" +
                            "\n" +
                            " \n" +
                            "\n" +
                            "intervallet, der g??lder er fra det f??rste vare til og med det sidste.",
                    project = Project.QQ,
                    priority = Priority.LOW,
                    status = Status.PAUSED,
                    issuetype = Issuetype.EPIC
                )
            ),
            IssueDto(
                id = "6",
                key = "WHT-374",
                fields = Fields(
                    summary = "nummerserie SAG er slettet og man kan ikke oprette Chartek",
                    description = "\"Slet nummerserie\"",
                    project = Project.WHT,
                    priority = Priority.NORMAL,
                    status = Status.REJECTED,
                    issuetype = Issuetype.IMPROVEMENT
                )
            ),
            IssueDto(
                id = "7",
                key = "ESBJ-248",
                fields = Fields(
                    summary = "Kranbooking - kranf??rer pr dag",
                    description = "Hej Trine\n" +
                            "\n" +
                            "Se svar med r??d l??ngere nede???\n" +
                            "\n" +
                            "Venlig hilsen\n" +
                            "\n" +
                            "Ole Palner\n" +
                            "Seniorkonsulent\n" +
                            "\n" +
                            "*Elbek & Vejrup A/S\n" +
                            "* Tangen 6 ??? 8200 Aarhus N\n" +
                            "Tlf.: 7020 2086 ??? elbek-vejrup.dk\n" +
                            "\n" +
                            "Se hvordan vi kan skabe forretningsm??ssig v??rdi:\n" +
                            "\n" +
                            "* \n" +
                            "*\n" +
                            "\n" +
                            "From: Trine Clausen, Esbjerg Havn <tc@portesbjerg.dk>\n" +
                            "\n" +
                            "Sent: 30. august 2021 12:53\n" +
                            "\n" +
                            "To: Ole Palner <OP@elbek-vejrup.dk>\n" +
                            "\n" +
                            "Cc: Flemming Aarhus Pedersen, Esbjerg Havn <FLP@portesbjerg.dk>\n" +
                            "\n" +
                            "Subject: Kranbooking - kranf??rer pr dag\n" +
                            "\n" +
                            "Hej Ole\n" +
                            "\n" +
                            "Nu er freden forbi ??? jeg er tilbage p?? kontoret igen \uD83D\uDE0A\uD83D\uDE0A\uD83D\uDE0A\n" +
                            "\n" +
                            "Vil det v??re muligt, at f?? en kolonne med\n" +
                            "skibsnavn ind p?? dette view ??? og hvor hurtigt kan det evt. oprettes klar til drift?\n" +
                            "Yep, jeg f??r det ind onsdag\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "Min visning ??? flot side - MEN\n" +
                            "kan den vise 7 dage frem fra dagsdato -\n" +
                            "Det mener jeg den allerede g??r, men tjekker lige op p?? det onsdag\n" +
                            "\n" +
                            "Er det ogs?? fra dette view, der vises data p?? hjemmesiden, hvis ja skal der vises booking for l??bende uge + 3 uger mere \uD83D\uDE0A\n" +
                            "Nope ikke fra det view (page) men direkte fra tabellen. Din hjemmesidemand har blot f??et tabelnavn ??? du skal derfor sp??rge ham hvis den ikke viser den korrekte tidshorisont\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "Med venlig hilsen / Best Regards\n" +
                            "Trine Clausen\n" +
                            "IT support\n" +
                            "\n" +
                            "\t\n" +
                            "+45 2341 2044 (direct)\tHulvejen 1\n" +
                            "+45 7612 4000 (main)\tDK-6700 Esbjerg\n" +
                            "tc@portesbjerg.dk\twww.portesbjerg.dk\n" +
                            "Venlig hilsen\n" +
                            "\n" +
                            "Ole Palner\n" +
                            "Seniorkonsulent\n" +
                            "\n" +
                            "*Elbek & Vejrup A/S\n" +
                            "* Tangen 6 ??? 8200 Aarhus N\n" +
                            "Tlf.: 7020 2086 ??? elbek-vejrup.dk\n" +
                            "\n" +
                            "Se hvordan vi kan skabe forretningsm??ssig v??rdi:\n" +
                            "\n" +
                            "* \n" +
                            "*2021-08-30_2137_OP_AT_elbek-vejrup.dk.msg",
                    project = Project.ESBJ,
                    priority = Priority.HIGH,
                    status = Status.REOPENED,
                    issuetype = Issuetype.NEW_FEATURE
                )
            ),
            IssueDto(
                id = "8",
                key = "ESBJ-243",
                fields = Fields(
                    summary = "Div tilretninger kranbooking",
                    description = "Dato filter rettes til: Today+6 dage frem p??:\n" +
                            "\n" +
                            "Page Crane Book. Operators Per Day (50524)\n" +
                            "\n" +
                            "Crane Bookings Per Day (50523)\n" +
                            "\n" +
                            "-------------------------------------------\n" +
                            "\n" +
                            "Tabel Crane Booking (50517):\n" +
                            "\n" +
                            "Felt 50450 Show on CP s??ttes til true ved oprettelse\n" +
                            "\n" +
                            "-----------------------------------------\n" +
                            "\n" +
                            "Page Crane Bookings (50522)\n" +
                            "\n" +
                            "Felt 50450 Show on CP s??ttes p?? Page. Skal kunne redigeres fra siden\n" +
                            "--------------------------\n" +
                            "\n" +
                            "Page Crane Bookings (50522)\n" +
                            "\n" +
                            "Der laves en SUBPAGE der viser PAGE Crane Operator Subpage (50530) p?? siden",
                    project = Project.ESBJ,
                    priority = Priority.LOW,
                    status = Status.RESOLVED,
                    issuetype = Issuetype.STORY
                )
            ),
            IssueDto(
                id = "9",
                key = "KOL-41",
                fields = Fields(
                    summary = "To nye stacks p?? rollecenter",
                    description = "To nye stacks:\n" +
                            "\n" +
                            "???Anl??b denne m??ned??? (Anl??b i havn (i indev??rende m??ned) (Filter p?? ankomstdato i indev??rende m??ned)\n" +
                            "???Anl??b ??r til dato??? (Afsejlede anl??b totalt) (Filter p?? ankomstdato i indev??rende ??r)\n" +
                            "\n" +
                            "Skal laves p?? disse tre rollecenter. (T??nker dog kun at du skal inds??tte de to stacks i ???aktivitets page??? Samme page bliver sikkert brugt i de forskellige rollecentre)\n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            " \n" +
                            "\n" +
                            "Rollecenter: Bogholder havn\n" +
                            "\n" +
                            "  \n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            " \n" +
                            "\n" +
                            "Venlig hilsen\n" +
                            "\n" +
                            "Ole Palner\n" +
                            "Seniorkonsulent\n" +
                            "\n" +
                            "*Elbek & Vejrup A/S\n" +
                            "\n" +
                            "Tangen 6 ??? 8200 Aarhus N\n" +
                            "Tlf.: 7020 2086 ??? elbek-vejrup.dk",
                    project = Project.KOL,
                    priority = Priority.BLOCKER,
                    status = Status.REVIEW,
                    issuetype = Issuetype.SUB_TASK
                )
            ),
            IssueDto(
                id = "10",
                key = "ESBJ-233",
                fields = Fields(
                    summary = "En Kran booking i NAV kan str??kke sig over flere dage p?? flere forskellige kranf??rer. ??nske om at kunne angive alle forskellige kranf??rer p?? samme Kranbooking",
                    description = "I vores kranbooking er vi n??dsaget til at oprette en ???opgave??? pr kranf??rer p?? et job. Det vil sige, at hvis vi har et skib inde, og de skal kranes over 3 dage ??? s?? give det mange linjer.\n" +
                            "Har du eller kodedrengene en ide til hvordan dette kan l??ses. Det vil v??re godt hvis der kan s??ttes flere kranf??re p?? det samme job, men s?? er jeg godt klar over at vi ikke kan specificere hvem der skal krane fra 7-16 og fra 16-22 osv.",
                    project = Project.ESBJ,
                    priority = Priority.NORMAL,
                    status = Status.PAUSED,
                    issuetype = Issuetype.SUPPORT
                )
            )
        )
    )

    val ISSUE_TEST_OBJECT = API_RESULT_TEST_OBJECT.toIssuesList().random()
}