package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object NoteTaskTable : Table() {

    override val tableName = "note_task"

    val noteId = reference("note_id", NoteTable.noteId)
    val taskId = reference("task_id", TaskTable.taskId)
}