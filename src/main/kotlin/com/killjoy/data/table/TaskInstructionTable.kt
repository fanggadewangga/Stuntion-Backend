package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object TaskInstructionTable: Table() {
    override val tableName: String = "task_instruction"

    val taskId = reference("task_id", TaskTable.taskId)
    val instruction = varchar("instruction", 512)

}