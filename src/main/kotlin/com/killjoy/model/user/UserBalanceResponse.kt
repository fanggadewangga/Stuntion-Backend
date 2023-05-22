package com.killjoy.model.user

import com.google.gson.annotations.SerializedName

data class UserBalanceResponse(
    @field:SerializedName("balance")
    val balance: Double,
)