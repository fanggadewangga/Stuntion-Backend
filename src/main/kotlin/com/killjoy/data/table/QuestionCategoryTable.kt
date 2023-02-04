package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object QuestionCategoryTable : Table() {
    override val tableName = "question_table"

    val questionId = reference("question_id", QuestionTable.questionId)
    val category = varchar("category", 24)
}