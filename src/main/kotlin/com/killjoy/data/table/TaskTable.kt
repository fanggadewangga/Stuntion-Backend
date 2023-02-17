package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object TaskTable : Table() {

    override val tableName = "task"

    val taskId = varchar("task_id", 128)
    val task = varchar("task", 128)
    val done = bool("done")

    override val primaryKey = PrimaryKey(taskId)
}