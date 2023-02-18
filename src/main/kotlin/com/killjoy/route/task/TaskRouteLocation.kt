@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.task

import io.ktor.locations.*

sealed class TaskRouteLocation {
    companion object {
        // GET
        private const val TASK = "/task/{uid}"
        private const val SELECTED_TASK = "$TASK/{taskId}"

        // POST
        const val POST_TASK = "/task"

        // GET
        private const val GET_USER_TASK = TASK

        // POST
        private const val POST_USER_TASK = TASK

        // GET
        private const val DETAIL_TASK = SELECTED_TASK
    }

    @Location(POST_TASK)
    class TaskAddRoute

    @Location(GET_USER_TASK)
    data class TaskGetByUserRoute(val uid:String)

    @Location(POST_USER_TASK)
    data class TaskPostByUserRoute(val uid: String)

    @Location(DETAIL_TASK)
    data class TaskGetDetailRoute(val uid: String,val taskId: String)
}
