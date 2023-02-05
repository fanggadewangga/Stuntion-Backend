package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object ExpertTable : Table() {

    override val tableName = "expert"

    val expertId = varchar("expert_id", 128)
    val name = varchar("name", 128)
    val avatarUrl = varchar("avatar_url", 128)
    val experienceYear = integer("experience_year")
    val str = varchar("str", 128)
    val fee = integer("fee")
    val rating = double("rating").nullable()

    override val primaryKey = PrimaryKey(expertId)
}