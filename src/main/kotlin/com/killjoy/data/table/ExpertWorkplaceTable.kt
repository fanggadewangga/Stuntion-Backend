package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object ExpertWorkplaceTable : Table() {

    override val tableName = "expert_workplace"

    val expertId = reference("expert_id", ExpertTable.expertId)
    val workplace = varchar("workplace", 64)
}