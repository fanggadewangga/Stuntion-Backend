package com.killjoy.model.user

import com.google.gson.annotations.SerializedName

data class UserGeneralInformationBody(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("birth_date")
    val birthDate: String,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)