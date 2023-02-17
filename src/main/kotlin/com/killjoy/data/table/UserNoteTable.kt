package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object UserNoteTable: Table() {

    override val tableName = "user_note"

    val userId = reference("uid", UserTable.uid)
    val noteId = reference("note_id", NoteTable.noteId)
}