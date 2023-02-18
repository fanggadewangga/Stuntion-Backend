package com.killjoy.route.user

import io.ktor.locations.*

class UserRouteLocation {
    companion object {
        private const val USER = "/user"

        // POST
        const val POST_USER = USER
        const val SELECTED_USER = "$USER/{uid}"

        // GET
        const val DETAIL_USER = SELECTED_USER

        // UPDATE
        const val UPDATE_USER = SELECTED_USER
        private const val UPDATE_USER_GENERAL_INFORMATION = "$UPDATE_USER/general"
    }

    @Location(POST_USER)
    class UserAddRoute

    @Location(DETAIL_USER)
    data class UserGetDetailRoute(val uid: String)

    @Location(UPDATE_USER_GENERAL_INFORMATION)
    data class UserUpdateGeneralInformationRoute(val uid: String)

}