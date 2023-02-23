package com.killjoy.util

import com.killjoy.data.table.ExpertTable
import com.killjoy.data.table.QuestionCategoryTable
import com.killjoy.data.table.QuestionTable
import com.killjoy.data.table.UserTable
import com.killjoy.model.expert.ExpertResponse
import com.killjoy.model.question.QuestionCategory
import com.killjoy.model.question.QuestionLiteResponse
import com.killjoy.model.question.QuestionResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToQuestionResponse(expert: ExpertResponse) = QuestionResponse(
    questionId = this[QuestionTable.questionId],
    title = this[QuestionTable.title],
    question = this[QuestionTable.question],
    answer = this[QuestionTable.answer],
    timestamp = this[QuestionTable.timestamp],
    userName = (if (this[QuestionTable.isAnonymous]) "Anonymous" else this[UserTable.name])!!,
    userAvatarUrl = this[UserTable.avatarUrl]!!,
    expertName = expert.name,
    expertAvatarUrl = expert.avatarUrl,
    expertExperience = expert.experienceYear,
    expertRating = expert.rating,
    expertCategories = expert.categories,
)

fun ResultRow.mapRowToQuestionLiteResponse(
    categories: List<QuestionCategory>,
) = QuestionLiteResponse(
    questionId = this[QuestionTable.questionId],
    title = this[QuestionTable.title],
    question = this[QuestionTable.question],
    timestamp = this[QuestionTable.timestamp],
    categories = categories
        .filter { it.questionId == this[QuestionTable.questionId] }
        .map { it.category },
    userName = (if (this[QuestionTable.isAnonymous]) "Anonymous" else this[UserTable.name])!!,
    userAvatarUrl = this[UserTable.avatarUrl]!!,
    expertName = this[ExpertTable.name],
    expertAvatarUrl = this[ExpertTable.avatarUrl]
)

fun ResultRow.mapRowToQuestionCategory() = QuestionCategory(
    questionId = this[QuestionCategoryTable.questionId],
    category = this[QuestionCategoryTable.category]
)