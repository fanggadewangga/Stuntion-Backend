@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.voucher

import com.killjoy.route.user.UserRouteLocation.Companion.SELECTED_USER
import io.ktor.locations.*

class VoucherRouteLocation {
    companion object{
        private const val VOUCHER = "/voucher"

        // POST
        const val POST_VOUCHER = VOUCHER

        // GET
        const val GET_VOUCHER = "$SELECTED_USER/$VOUCHER"
    }

    @Location(POST_VOUCHER)
    class VoucherAddRoute

    @Location(GET_VOUCHER)
    class VoucherGetListRoute(val uid: String)
}