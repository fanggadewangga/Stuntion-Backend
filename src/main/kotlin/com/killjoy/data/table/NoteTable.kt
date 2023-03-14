package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object NoteTable: Table() {

    override val tableName = "note"

    val noteId = varchar("note_id", 128)
    val uid = reference("uid", UserTable.uid)
    val name = varchar("name", 64)
    val gender = varchar("gender", 48)
    val birthDay = varchar("birthday", 64).nullable()
    val ageYear = integer("age_year")
    val ageMonth = integer("age_month")
    val ageDay = integer("age_day")
    val height = double("height")
    val heightDescription = varchar("height_description", 64).nullable()
    val weight = double("weight")
    val weightDescription = varchar("weight_description", 64).nullable()
    val idealHeight = double("ideal_height").nullable()
    val idealWeight = double("ideal_weight").nullable()
    val timestamp = varchar("timestamp", 128)

    override val primaryKey = PrimaryKey(noteId)
}