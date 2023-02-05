package com.killjoy.model.expert

import com.google.gson.annotations.SerializedName

data class ExpertLiteResponse(

    @field:SerializedName("expert_id")
    val expertId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("experience_year")
    val experienceYear: Int,

    @field:SerializedName("fee")
    val fee: Int,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("categories")
    val categories: List<String>,

)
