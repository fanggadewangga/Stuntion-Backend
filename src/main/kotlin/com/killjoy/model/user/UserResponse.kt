package com.killjoy.model.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("birth_date")
    val birthDate: String?,

    @field:SerializedName("gender")
    val gender: String?,

    @field:SerializedName("avatar_url")
    val avatarUrl: String?,

    @field:SerializedName("balance")
    val balance: Double?,

    @field:SerializedName("xp")
    val xp: Int,

    @field:SerializedName("level")
    val level: Int,
)
