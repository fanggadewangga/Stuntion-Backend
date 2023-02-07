package com.killjoy.route.question

import io.ktor.locations.*

sealed class QuestionRouteLocation {
    companion object {
        // GET (include query to search job)
        private const val QUESTION = "/question"

        // POST
        const val POST_QUESTION = QUESTION
        private const val SELECTED_QUESTION = "$QUESTION/{questionId}"

        // GET
        const val DETAIL_QUESTION = SELECTED_QUESTION
    }

    @Location(QUESTION)
    class QuestionGetListRoute

    @Location(POST_QUESTION)
    class QuestionAddRoute

    @Location(DETAIL_QUESTION)
    data class QuestionGetDetailRoute(val questionId: String)
}
