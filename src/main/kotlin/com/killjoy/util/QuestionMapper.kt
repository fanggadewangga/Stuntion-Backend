package com.killjoy.util

import com.killjoy.data.table.*
import com.killjoy.model.expert.ExpertCategory
import com.killjoy.model.question.QuestionCategory
import com.killjoy.model.question.QuestionLiteResponse
import com.killjoy.model.question.QuestionResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToQuestionResponse(
    categories: List<String>,
    userName: String,
    userAvatarUrl: String,
    expertName: String?,
    expertAvatarUrl: String?,
    expertExperience: Int?,
    expertRating: Double?,
    expertCategory: List<ExpertCategory>?,
) = QuestionResponse(
    questionId = this[QuestionTable.questionId],
    title = this[QuestionTable.title],
    question = this[QuestionTable.question],
    answer = this[QuestionTable.answer],
    timestamp = this[QuestionTable.timestamp],
    categories = categories,
    userName = if (this[QuestionTable.isAnonymous]) "Anonymous" else userName,
    userAvatarUrl = userAvatarUrl,
    expertName = expertName,
    expertAvatarUrl = expertAvatarUrl,
    expertExperience = expertExperience,
    expertRating = expertRating,
    expertCategories = expertCategory
        ?.filter { it.expertId == this[ExpertTable.expertId] }
        ?.map { it.category },
)

fun ResultRow.mapRowToQuestionLiteResponse(
    categories: List<QuestionCategory>,
) = (if (this[QuestionTable.isAnonymous]) "Anonymous" else this[UserTable.name])?.let {
    this[UserTable.avatarUrl]?.let { it1 ->
        QuestionLiteResponse(
            questionId = this[QuestionTable.questionId],
            title = this[QuestionTable.title],
            question = this[QuestionTable.question],
            timestamp = this[QuestionTable.timestamp],
            categories = categories
                .filter { it.questionId == this[QuestionTable.questionId] }
                .map { it.category },
            userName = it,
            userAvatarUrl = it1,
            expertName = this[ExpertTable.name],
            expertAvatarUrl = this[ExpertTable.avatarUrl]
        )
    }
}

fun ResultRow.mapRowToQuestionCategory() = QuestionCategory(
    questionId = this[QuestionCategoryTable.questionId],
    category = this[QuestionCategoryTable.category]
)