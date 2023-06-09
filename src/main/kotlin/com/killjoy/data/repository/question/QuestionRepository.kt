package com.killjoy.data.repository.question

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.*
import com.killjoy.model.question.QuestionBody
import com.killjoy.model.question.QuestionExpertAnswerBody
import com.killjoy.model.question.QuestionLiteResponse
import com.killjoy.model.question.QuestionResponse
import com.killjoy.util.*
import org.jetbrains.exposed.sql.*
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

    override suspend fun addExpertAnswer(questionId: String, body: QuestionExpertAnswerBody) {
        dbFactory.dbQuery {
            QuestionTable.update({ QuestionTable.questionId.eq(questionId) }) {
                it[expertId] = body.expertId
                it[answer] = body.answer
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
            .join(ExpertTable, JoinType.LEFT) { QuestionTable.expertId.eq(ExpertTable.expertId) }
            .slice(
                QuestionTable.questionId,
                QuestionTable.title,
                QuestionTable.question,
                QuestionTable.timestamp,
                QuestionTable.isAnonymous,
                UserTable.name,
                UserTable.avatarUrl,
                ExpertTable.name,
                ExpertTable.avatarUrl
            )
            .selectAll()
            .mapNotNull { it.mapRowToQuestionLiteResponse(listOfQuestionCategory) }
    }

    override suspend fun searchQuestion(query: String): List<QuestionLiteResponse> = dbFactory.dbQuery {
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
            .join(ExpertTable, JoinType.LEFT) { QuestionTable.expertId.eq(ExpertTable.expertId) }
            .slice(
                QuestionTable.questionId,
                QuestionTable.title,
                QuestionTable.question,
                QuestionTable.timestamp,
                QuestionTable.isAnonymous,
                UserTable.name,
                UserTable.avatarUrl,
                ExpertTable.name,
                ExpertTable.avatarUrl
            )
            .select { LowerCase(QuestionTable.title).like("%$query%".lowercase(Locale.getDefault())) }
            .mapNotNull { it.mapRowToQuestionLiteResponse(listOfQuestionCategory) }
    }

    override suspend fun getQuestionDetail(questionId: String): QuestionResponse = dbFactory.dbQuery {
        val expertId = QuestionTable.select { QuestionTable.questionId.eq(questionId) }
            .firstNotNullOf { it[QuestionTable.expertId] }
        val expertCategory = ExpertCategoryTable.select { ExpertCategoryTable.expertId.eq(expertId) }
            .mapNotNull { it.mapRowToExpertCategory() }
        val workplaces = ExpertWorkplaceTable.select { ExpertWorkplaceTable.expertId eq expertId }
            .mapNotNull { it.mapRowToExpertWorkplace() }
        val educations = ExpertEducationTable.select { ExpertEducationTable.expertId eq expertId }
            .mapNotNull { it.mapRowToExpertEducation() }
        val expert = ExpertTable.select { ExpertTable.expertId eq expertId }
            .firstNotNullOf { it.mapRowToExpertResponse(expertCategory, workplaces, educations) }

        QuestionTable
            .join(UserTable, JoinType.INNER) { QuestionTable.uid.eq(UserTable.uid) }
            .slice(
                QuestionTable.questionId,
                QuestionTable.question,
                QuestionTable.title,
                QuestionTable.answer,
                QuestionTable.timestamp,
                QuestionTable.isAnonymous,
                UserTable.name,
                UserTable.avatarUrl,
            )
            .select { QuestionTable.questionId eq questionId }
            .firstNotNullOf { it.mapRowToQuestionResponse(expert) }
    }
}
