@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.payment

import io.ktor.locations.*

class PaymentRouteLocation {
    companion object{
        private const val PAYMENT = "/payment"

        // POST
        const val POST_PAYMENT = PAYMENT

        // GET
        const val GET_PAYMENT = PAYMENT
    }

    @Location(POST_PAYMENT)
    class PaymentAddRoute

    @Location(GET_PAYMENT)
    class PaymentGetListRoute
}