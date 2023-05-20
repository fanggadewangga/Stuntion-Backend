@file:OptIn(KtorExperimentalLocationsAPI::class, KtorExperimentalLocationsAPI::class)

package com.killjoy.route.donation

import com.killjoy.data.repository.donation.IDonationRepository
import com.killjoy.model.donation.DonationBody
import com.killjoy.model.donation.DonorBody
import com.killjoy.route.RouteResponseHelper.generalException
import com.killjoy.route.RouteResponseHelper.generalListSuccess
import com.killjoy.route.RouteResponseHelper.generalSuccess
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.request.*
import io.ktor.routing.*

class DonationRoute(
    private val repository: IDonationRepository
) {
    private fun Route.addNewDonation() {
        post<DonationRouteLocation.DonationAddRoute> {
            val body = try {
                call.receive<DonationBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewDonation(body) }
        }
    }

    private fun Route.getAllDonations() {
        get<DonationRouteLocation.DonationGetListRoute> {
            val query = try {
                call.request.queryParameters["q"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }

            if (query != null)
                call.generalSuccess { repository.searchDonation(query) }
            else
                call.generalSuccess { repository.getAllDonations() }
        }
    }

    private fun Route.getDonationDetail() {
        get<DonationRouteLocation.DonationGetDetailRoute> {
            val donationId = try {
                call.parameters["donationId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }
            call.generalSuccess { repository.getDonationDetail(donationId!!) }
        }
    }

    private fun Route.updateDonationCurrentValue() {
        put<DonationRouteLocation.DonationUpdateCurrentValueRoute> {
            val donationId = try {
                call.parameters["donationId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@put
            }
            call.generalSuccess { repository.updateDonationCurrentValue(donationId!!) }
        }
    }

    private fun Route.deleteDonation() {
        delete<DonationRouteLocation.DonationUpdateCurrentValueRoute> {
            val donationId = try {
                call.parameters["donationId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@delete
            }
            call.generalSuccess { repository.deleteDonation(donationId!!) }
        }
    }

    private fun Route.addNewDonor() {
        post<DonationRouteLocation.DonationAddDonorRoute> {
            val donationId = try {
                call.parameters["donationId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }

            val body = try {
                call.receive<DonorBody>()
            } catch (e: Exception) {
                call.generalException(e)
                return@post
            }
            call.generalSuccess { repository.addNewDonor(body, donationId!!) }
        }
    }

    private fun Route.getDonationDonors() {
        get<DonationRouteLocation.DonationGetDonorsRoute> {
            val donationId = try {
                call.parameters["donationId"]
            } catch (e: Exception) {
                call.generalException(e)
                return@get
            }

            call.generalListSuccess { repository.getAllDonors(donationId!!) }
        }
    }

    fun initDonationRoute(route: Route) {
        route.apply {
            addNewDonation()
            getAllDonations()
            getDonationDetail()
            updateDonationCurrentValue()
            deleteDonation()
            addNewDonor()
            getDonationDonors()
        }
    }
}