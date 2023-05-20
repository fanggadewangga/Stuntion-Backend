@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.killjoy.route.donation

import io.ktor.locations.*

sealed class DonationRouteLocation {
    companion object {
        // GET (include query to search donation)
        private const val DONATION = "/donation"

        // POST
        const val POST_DONATION = DONATION
        private const val SELECTED_DONATION = "$DONATION/{donationId}"
        const val POST_DONATION_DONOR = "$SELECTED_DONATION/donor"

        // GET
        const val DETAIL_DONATION = SELECTED_DONATION
        const val DONATION_DONOR = "$SELECTED_DONATION/donor"

        // UPDATE
        const val UPDATE_DONATION_CURRENT_VALUE = SELECTED_DONATION

        // DELETE
        const val DELETE_DONATION = SELECTED_DONATION
    }

    @Location(DONATION)
    class DonationGetListRoute

    @Location(POST_DONATION)
    class DonationAddRoute

    @Location(DETAIL_DONATION)
    data class DonationGetDetailRoute(val donationId: String)

    @Location(UPDATE_DONATION_CURRENT_VALUE)
    data class DonationUpdateCurrentValueRoute(val donationId: String)

    @Location(DELETE_DONATION)
    data class DonationDeleteRoute(val donationId: String)

    @Location(POST_DONATION_DONOR)
    data class DonationAddDonorRoute(val donationId: String)

    @Location(DONATION_DONOR)
    data class DonationGetDonorsRoute(val donationId: String)
}