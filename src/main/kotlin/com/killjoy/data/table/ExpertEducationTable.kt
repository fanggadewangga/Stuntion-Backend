package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object ExpertEducationTable : Table() {

    override val tableName = "expert_education"

    val expertId = reference("expert_id", ExpertTable.expertId)
    val university = varchar("university", 64)
}