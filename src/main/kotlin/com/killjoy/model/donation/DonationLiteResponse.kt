package com.killjoy.model.donation

import com.google.gson.annotations.SerializedName

data class DonationLiteResponse(
    @field:SerializedName("donation_id")
    val donationId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("current_value")
    val currentValue: Int,

    @field:SerializedName("max_value")
    val maxValue: Int,

    @field:SerializedName("nominal")
    val currentNominal: Double,

    @field:SerializedName("day_remaining")
    val dayRemaining: Int,

    @field:SerializedName("fee")
    val fee: Int,

    @field:SerializedName("lat")
    val lat: Double,

    @field:SerializedName("lon")
    val lon: Double
)
