package com.killjoy.route.article

import com.killjoy.data.repository.article.IArticleRepository
import com.killjoy.model.article.ArticleBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalListSuccess
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.routing.*

class ArticleRoute(
    private val repository: IArticleRepository
) {

    private fun Route.addNewArticle() {
        post<ArticleRouteLocation.ArticleAddRoute> {
            val body = try {
                call.receive<ArticleBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewArticle(body) }
        }
    }

    private fun Route.getAllArticles() {
        get<ArticleRouteLocation.ArticleGetListRoute> {
            val query = try {
                call.request.queryParameters["q"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }

            if (query != null)
                call.generalListSuccess { repository.searchArticle(query) }
            else
                call.generalSuccess { repository.getAllArticles() }
        }
    }

    private fun Route.getArticleDetail() {
        get<ArticleRouteLocation.ArticleGetDetailRoute> {
            val articleId = try {
                call.parameters["articleId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getArticleDetail(articleId!!) }
        }
    }

    fun initArticleRoute(route: Route) {
        route.apply {
            addNewArticle()
            getAllArticles()
            getArticleDetail()
        }
    }
}