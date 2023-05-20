package com.killjoy.model.donation

import com.google.gson.annotations.SerializedName

data class DonorResponse(
    @field:SerializedName("name")
    val donorName: String,

    @field:SerializedName("nominal")
    val nominal: Double,

    @field:SerializedName("timestamp")
    val timestamp: String,

    @field:SerializedName("period")
    val dayPeriod: Int,
)