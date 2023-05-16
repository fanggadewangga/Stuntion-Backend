package com.killjoy.model.user

import com.google.gson.annotations.SerializedName

data class UserBalanceBody(
    @field:SerializedName("balance")
    val balance: Double,
)
