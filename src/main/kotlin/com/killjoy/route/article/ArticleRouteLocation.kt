package com.killjoy.route.article

import io.ktor.locations.*

sealed class ArticleRouteLocation {
    companion object {
        // GET (include query to search job)
        private const val ARTICLE = "/article"

        // POST
        const val POST_ARTICLE = ARTICLE
        private const val SELECTED_ARTICLE = "$ARTICLE/{articleId}"

        // GET
        const val DETAIL_ARTICLE = SELECTED_ARTICLE
    }

    @Location(ARTICLE)
    class ArticleGetListRoute

    @Location(POST_ARTICLE)
    class ArticleAddRoute

    @Location(DETAIL_ARTICLE)
    data class ArticleGetDetailRoute(val articleId: String)

}