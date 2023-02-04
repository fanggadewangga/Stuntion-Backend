package com.killjoy.data.table

import org.jetbrains.exposed.sql.Table

object QuestionTable : Table() {

    override val tableName = "question"

    val questionId = varchar("question_id", 128)
    val uid = reference("uid", UserTable.uid)
    val expertId = reference("expert_id", ExpertTable.expertId).nullable()
    val title = varchar("title", 128)
    val question = varchar("question", 512)
    val answer = varchar("answer", 512).nullable()
    val isAnonymous = bool("isAnonymous")
    val timestamp = varchar("time_stamp", 64)

    override val primaryKey = PrimaryKey(questionId)
}