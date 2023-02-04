package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {

    override val tableName = "user"

    val uid = varchar("uid", 128)
    val email = varchar("email", 64)
    val name = varchar("name", 128).nullable()
    val birthDate = varchar("birth_date", 64).nullable()
    val gender = varchar("gender", 24).nullable()
    val avatar = varchar("avatar", 512).nullable()
    val xp = integer("xp")

    override val primaryKey = PrimaryKey(uid)
}