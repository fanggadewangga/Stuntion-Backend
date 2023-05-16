package com.killjoy.model.payment

import com.google.gson.annotations.SerializedName

data class PaymentBody(

    @field:SerializedName("payment_name")
    val paymentName: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("payment_type")
    val type: Int,

    )
