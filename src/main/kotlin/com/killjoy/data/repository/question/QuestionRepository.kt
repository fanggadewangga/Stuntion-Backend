package com.killjoy.data.repository.question

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.*
import com.killjoy.model.question.QuestionBody
import com.killjoy.model.question.QuestionLiteResponse
import com.killjoy.model.question.QuestionResponse
import com.killjoy.util.*
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class QuestionRepository(private val dbFactory: DatabaseFactory) : IQuestionRepository {
    override suspend fun addNewQuestion(body: QuestionBody) {

        dbFactory.dbQuery {
            val dateObj = Date()
            val df: DateFormat = SimpleDateFormat("dd MMMM yyyy")
            val dateCreated = df.format(dateObj)
            val idCreated = "QUESTION-${NanoIdUtils.randomNanoId()}"

            QuestionTable.insert {
                it[questionId] = idCreated
                it[uid] = body.uid
                it[title] = body.title
                it[question] = body.question
                it[isAnonymous] = body.isAnonymous
                it[timestamp] = dateCreated
            }

            body.categories.forEach { category ->
                QuestionCategoryTable.insert {
                    it[questionId] = idCreated
                    it[this.category] = category
                }
            }
        }
    }

    override suspend fun getAllQuestions(): List<QuestionLiteResponse> = dbFactory.dbQuery {
        val listOfQuestionId = QuestionTable.selectAll().map { it[QuestionTable.questionId] }

        val listOfQuestionCategory = listOfQuestionId.map { questionId ->
            QuestionCategoryTable.select {
                QuestionCategoryTable.questionId eq questionId
            }.mapNotNull {
                it.mapRowToQuestionCategory()
            }
        }.flatten()

        QuestionTable
            .join(UserTable, JoinType.INNER) { QuestionTable.uid.eq(UserTable.uid) }
            .join(ExpertTable, JoinType.INNER) { QuestionTable.expertId.eq(ExpertTable.expertId) }
            .slice(
                QuestionTable.questionId,
                QuestionTable.title,
                QuestionTable.question,
                QuestionTable.timestamp,
                UserTable.name,
                UserTable.avatarUrl,
                ExpertTable.name,
                ExpertTable.avatarUrl
            )
            .selectAll()
            .mapNotNull { it.mapRowToQuestionLiteResponse(listOfQuestionCategory) }
    }

    override suspend fun searchQuestion(query: String): List<QuestionLiteResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getQuestionDetail(questionId: String): QuestionResponse {
        TODO("Not yet implemented")
    }
}
