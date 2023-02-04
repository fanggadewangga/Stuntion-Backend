package com.killjoy.model.user

import com.google.gson.annotations.SerializedName

data class UserBody (
    @field:SerializedName("uid")
    val uid : String,

    @field:SerializedName("email")
    val email: String
)