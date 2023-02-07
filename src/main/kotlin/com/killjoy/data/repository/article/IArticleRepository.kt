package com.killjoy.data.repository.article

import com.killjoy.model.article.ArticleBody
import com.killjoy.model.article.ArticleLiteResponse
import com.killjoy.model.article.ArticleResponse

interface IArticleRepository {
    suspend fun addNewArticle(body : ArticleBody) // clear
    suspend fun getArticleDetail(articleId : String): ArticleResponse // clear
    suspend fun getAllArticles() : List<ArticleLiteResponse> // clear
    suspend fun searchArticle(query: String) : List<ArticleLiteResponse> // clear
}