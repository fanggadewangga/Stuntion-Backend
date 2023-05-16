@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.voucher

import com.killjoy.data.repository.voucher.IVoucherRepository
import com.killjoy.model.voucher.VoucherBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.routing.*

class VoucherRoute(
    private val repository: IVoucherRepository
) {
    private fun Route.addNewVoucher() {
        post<VoucherRouteLocation.VoucherAddRoute> {
            val body = try {
                call.receive<VoucherBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewVoucher(body) }
        }
    }

    private fun Route.getAllVouchers() {
        get<VoucherRouteLocation.VoucherGetListRoute> {
            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getAllVouchers(uid!!) }
        }
    }

    fun initVoucherRoute(route: Route) {
        route.apply {
            addNewVoucher()
            getAllVouchers()
        }
    }
}