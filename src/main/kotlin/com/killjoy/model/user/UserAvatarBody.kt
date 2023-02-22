package com.killjoy.model.user

import com.google.gson.annotations.SerializedName

data class UserAvatarBody(
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)
