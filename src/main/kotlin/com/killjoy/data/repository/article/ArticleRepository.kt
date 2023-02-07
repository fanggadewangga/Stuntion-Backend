package com.killjoy.data.repository.article

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.killjoy.data.database.DatabaseFactory
import com.killjoy.data.table.ArticleCategoryTable
import com.killjoy.data.table.ArticleTable
import com.killjoy.data.table.ExpertCategoryTable
import com.killjoy.data.table.ExpertTable
import com.killjoy.model.article.ArticleBody
import com.killjoy.model.article.ArticleLiteResponse
import com.killjoy.model.article.ArticleResponse
import com.killjoy.util.*
import org.jetbrains.exposed.sql.LowerCase
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ArticleRepository(private val dbFactory: DatabaseFactory) : IArticleRepository {
    override suspend fun addNewArticle(body: ArticleBody) =
        dbFactory.dbQuery {
            val dateObj = Date()
            val df: DateFormat = SimpleDateFormat("dd MMMM yyyy")
            val dateCreated = df.format(dateObj)
            val idCreated = "ARTICLE-${NanoIdUtils.randomNanoId()}"

            ArticleTable.insert { table ->
                table[articleId] = idCreated
                table[title] = body.title
                table[description] = body.description
                table[imageUrl] = body.imageUrl
                table[reviewer] = body.reviewer
                table[timestamp] = dateCreated
            }

            body.categories.forEach { category ->
                ArticleCategoryTable.insert {
                    it[this.articleId] = idCreated
                    it[this.category] = category
                }
            }
        }

    override suspend fun getArticleDetail(articleId: String): ArticleResponse = dbFactory.dbQuery {
        ArticleTable.select {
            ArticleTable.articleId.eq(articleId)
        }.firstNotNullOf {
            it.mapRowToArticleResponse()
        }
    }

    override suspend fun getAllArticles(): List<ArticleLiteResponse> = dbFactory.dbQuery {

        val listOfArticleId = ArticleTable.selectAll().map { it[ArticleTable.articleId] }

        val listOfArticleCategory = listOfArticleId.map { articleId ->
            ArticleCategoryTable.select {
                ArticleCategoryTable.articleId eq articleId
            }.mapNotNull {
                it.mapRowToArticleCategory()
            }
        }.flatten()

        ArticleTable.selectAll().mapNotNull {
            it.mapRowToArticleLiteResponse(listOfArticleCategory)
        }
    }

    override suspend fun searchArticle(query: String): List<ArticleLiteResponse> = dbFactory.dbQuery {
        val listOfArticleId = ArticleTable.selectAll().map { it[ArticleTable.articleId] }

        val listOfArticleCategory = listOfArticleId.map { articleId ->
            ArticleCategoryTable.select {
                ArticleCategoryTable.articleId eq articleId
            }.mapNotNull {
                it.mapRowToArticleCategory()
            }
        }.flatten()

        ArticleTable.select {
            LowerCase(ArticleTable.title).like("%$query%".lowercase(Locale.getDefault()))
        }.groupBy(ArticleTable.articleId)
            .mapNotNull {
                it.mapRowToArticleLiteResponse(listOfArticleCategory)
            }
    }
}