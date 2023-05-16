package com.killjoy.route.payment

import com.killjoy.data.repository.payment.IPaymentRepository
import com.killjoy.model.payment.PaymentBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.routing.*

@OptIn(KtorExperimentalLocationsAPI::class)
class PaymentRoute(private val repository: IPaymentRepository) {
    private fun Route.addNewPayment() {
        post<PaymentRouteLocation.PaymentAddRoute> {
            val body = try {
                call.receive<PaymentBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewPayment(body) }
        }
    }

    private fun Route.getAllPayments() {
        get<PaymentRouteLocation.PaymentGetListRoute> {
            call.generalSuccess { repository.getAllPayments() }
        }
    }

    fun initPaymentRoute(route: Route) {
        route.apply {
            addNewPayment()
            getAllPayments()
        }
    }
}