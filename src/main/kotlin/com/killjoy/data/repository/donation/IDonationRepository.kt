package com.killjoy.data.repository.donation

import com.killjoy.model.donation.*

interface IDonationRepository {
    suspend fun addNewDonation(body: DonationBody) // clear
    suspend fun updateDonationCurrentValue(donationId: String) // clear
    suspend fun getAllDonations(): List<DonationLiteResponse>  // clear
    suspend fun searchDonation(query: String): List<DonationLiteResponse>  // clear
    suspend fun getDonationDetail(donationId: String): DonationResponse // clear
    suspend fun deleteDonation(donationId: String)  // clear
    suspend fun addNewDonor(body: DonorBody) // not clear
    suspend fun getAllDonors(donationId: String): List<DonorResponse>
}