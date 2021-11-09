package com.example.jiralogger.domain.use_case.work_log

data class WorkLogUseCases(
    val getWorkLogs: GetWorkLogs,
    val deleteWorkLog: DeleteWorkLog,
    val insertWorkLog: InsertWorkLog,
    val getWorkLog: GetWorkLog
)