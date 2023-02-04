package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object ExpertCategoryTable : Table() {

    override val tableName = "expert_category"

    val expertId = reference("expert_id", ExpertTable.expertId)
    val category = varchar("category", 24)
}