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
        const val WALLET_USER = "$SELECTED_USER/wallet"

        // UPDATE
        const val UPDATE_USER = SELECTED_USER
        private const val UPDATE_USER_GENERAL_INFORMATION = "$UPDATE_USER/general"
        private const val UPDATE_USER_LEVEL = "$UPDATE_USER/level"
        private const val UPDATE_USER_AVATAR = "$UPDATE_USER/avatar"
        private const val UPDATE_USER_WALLET= "$UPDATE_USER/wallet"
    }

    @Location(POST_USER)
    class UserAddRoute

    @Location(DETAIL_USER)
    data class UserGetDetailRoute(val uid: String)

    @Location(WALLET_USER)
    data class UserGetWalletRoute(val uid: String)

    @Location(UPDATE_USER_GENERAL_INFORMATION)
    data class UserUpdateGeneralInformationRoute(val uid: String)

    @Location(UPDATE_USER_LEVEL)
    data class UserUpdateLevel(val uid: String)

    @Location(UPDATE_USER_AVATAR)
    data class UserUpdateAvatar(val uid: String)

    @Location(UPDATE_USER_WALLET)
    data class UserUpdateWallet(val uid: String)
}