package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object NoteTable: Table() {

    override val tableName = "note"

    val noteId = varchar("note_id", 128)
    val name = varchar("name", 64)
    val gender = varchar("gender", 48)
    val ageYear = integer("age_year")
    val ageMonth = integer("age_month")
    val ageDay = integer("age_day")
    val height = double("height")
    val weight = double("weight")
    val idealHeight = double("ideal_height")
    val idealWeight = double("ideal_weight")

    override val primaryKey = PrimaryKey(noteId)
}