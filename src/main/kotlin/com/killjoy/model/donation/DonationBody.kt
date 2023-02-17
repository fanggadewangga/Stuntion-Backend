package com.killjoy.model.donation

import com.google.gson.annotations.SerializedName

data class DonationBody(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("story")
    val story: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("deadline_at")
    val deadlineAt: String,

    @field:SerializedName("max_value")
    val maxValue: Int,

    @field:SerializedName("fee")
    val fee: Int,

    @field:SerializedName("lat")
    val lat: Double,

    @field:SerializedName("lon")
    val lon: Double
)
