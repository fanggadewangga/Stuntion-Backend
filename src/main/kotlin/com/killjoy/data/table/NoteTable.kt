package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object NoteTable: Table() {

    override val tableName = "note"

    val noteId = varchar("note_id", 128)
    val uid = reference("uid", UserTable.uid)
    val name = varchar("name", 64)
    val gender = varchar("gender", 48)
    val ageYear = integer("age_year")
    val ageMonth = integer("age_month")
    val ageDay = integer("age_day")
    val height = double("height")
    val weight = double("weight")
    val timestamp = varchar("timestamp", 128)

    override val primaryKey = PrimaryKey(noteId)
}