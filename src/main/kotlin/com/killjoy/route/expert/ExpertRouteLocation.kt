@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.expert

import io.ktor.locations.*

sealed class ExpertRouteLocation {
    companion object {
        // GET (include query to search job)
        private const val EXPERT = "/expert"

        // POST
        const val POST_EXPERT = EXPERT
        private const val SELECTED_EXPERT = "$EXPERT/{expertId}"

        // GET
        const val DETAIL_EXPERT = SELECTED_EXPERT
    }

    @Location(EXPERT)
    class ExpertGetListRoute

    @Location(POST_EXPERT)
    class ExpertAddRoute

    @Location(DETAIL_EXPERT)
    data class ExpertGetDetailRoute(val expertId: String)
}