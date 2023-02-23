package com.killjoy.util

import com.killjoy.data.table.ArticleCategoryTable
import com.killjoy.data.table.ArticleTable
import com.killjoy.model.article.ArticleCategory
import com.killjoy.model.article.ArticleLiteResponse
import com.killjoy.model.article.ArticleResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.mapRowToArticleResponse() = ArticleResponse(
    articleId = this[ArticleTable.articleId],
    title = this[ArticleTable.title],
    description = this[ArticleTable.description],
    videoUrl = this[ArticleTable.videoUrl],
    reviewer = this[ArticleTable.reviewer],
    timestamp = this[ArticleTable.timestamp],
)

fun ResultRow.mapRowToArticleLiteResponse(
    categories: List<ArticleCategory>
) = ArticleLiteResponse(
    articleId = this[ArticleTable.articleId],
    title = this[ArticleTable.title],
    description = this[ArticleTable.description],
    videoUrl = this[ArticleTable.videoUrl],
    categories = categories
        .filter { it.articleId == this[ArticleTable.articleId] }
        .map { it.category }
)

fun ResultRow.mapRowToArticleCategory() = ArticleCategory(
    articleId = this[ArticleCategoryTable.articleId],
    category = this[ArticleCategoryTable.category]
)