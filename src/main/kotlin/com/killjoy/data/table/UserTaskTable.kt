package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object UserTaskTable: Table() {

    override val tableName: String = "user_task"

    val taskId = reference("task_id", TaskTable.taskId)
    val uid = reference("uid", UserTable.uid)
    val done = bool("done")
}