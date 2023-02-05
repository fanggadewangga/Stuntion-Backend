package com.killjoy.util

import com.killjoy.data.table.ArticleTable
import com.killjoy.data.table.ExpertTable
import com.killjoy.data.table.QuestionTable
import com.killjoy.model.article.ArticleLiteResponse
import com.killjoy.model.article.ArticleResponse
import com.killjoy.model.expert.ExpertLiteResponse
import com.killjoy.model.expert.ExpertResponse
import com.killjoy.model.question.QuestionLiteResponse
import com.killjoy.model.question.QuestionResponse
import org.jetbrains.exposed.sql.Avg
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToArticleResponse(
    categories: List<String>,
) = ArticleResponse(
    articleId = this[ArticleTable.articleId],
    title = this[ArticleTable.title],
    description = this[ArticleTable.description],
    imageUrl = this[ArticleTable.imageUrl],
    reviewer = this[ArticleTable.reviewer],
    timestamp = this[ArticleTable.timestamp],
    categories = categories
)

fun ResultRow.mapRowToArticleLiteResponse(
    categories: List<String>
) = ArticleLiteResponse(
    articleId = this[ArticleTable.articleId],
    title = this[ArticleTable.title],
    description = this[ArticleTable.description],
    imageUrl = this[ArticleTable.imageUrl],
    categories = categories
)

fun ResultRow.mapRowToExpertResponse(
    categories: List<String>,
    workplaces: List<String>
) = ExpertResponse(
    expertId = this[ExpertTable.expertId],
    name = this[ExpertTable.name],
    avatarUrl = this[ExpertTable.avatarUrl],
    experienceYear = this[ExpertTable.experienceYear],
    str = this[ExpertTable.str],
    fee = this[ExpertTable.fee],
    rating = this[Avg(ExpertTable.rating, 1)]?.toDouble() ?: 0.0,
    categories = categories,
    workplaces = workplaces
)

fun ResultRow.mapRowToExpertLiteResponse(
    categories: List<String>
) = ExpertLiteResponse(
    expertId = this[ExpertTable.expertId],
    name = this[ExpertTable.name],
    avatarUrl = this[ExpertTable.avatarUrl],
    experienceYear = this[ExpertTable.experienceYear],
    fee = this[ExpertTable.fee],
    rating = this[Avg(ExpertTable.rating, 1)]?.toDouble() ?: 0.0,
    categories = categories,
)

fun ResultRow.mapRowToQuestionResponse(
    categories: List<String>,
    userName: String,
    userAvatarUrl: String,
    expertName: String?,
    expertAvatarUrl: String?,
    expertExperience: Int?,
    expertRating: Double?,
    expertCategory: List<String>?,
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
)

fun ResultRow.mapRowToQuestionLiteResponse(
    categories: List<String>,
    userName: String,
    userAvatarUrl: String,
    expertName: String?,
    expertAvatarUrl: String?,
) = QuestionLiteResponse(
    questionId = this[QuestionTable.questionId],
    title = this[QuestionTable.title],
    question = this[QuestionTable.question],
    timestamp = this[QuestionTable.timestamp],
    categories = categories,
    userName = if (this[QuestionTable.isAnonymous]) "Anonymous" else userName,
    userAvatarUrl = userAvatarUrl,
    expertName = expertName,
    expertAvatarUrl = expertAvatarUrl
)