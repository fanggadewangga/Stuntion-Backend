package com.killjoy.model.voucher

import com.google.gson.annotations.SerializedName

data class VoucherBody(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("voucher_type")
    val type: Int,

    @field:SerializedName("discount")
    val discount: Int?,

    @field:SerializedName("free_balance")
    val freeBalance: Double?,

    @field:SerializedName("valid_level")
    val validLevel: Int,

    @field:SerializedName("image_url")
    val imageUrl: String,

    )
