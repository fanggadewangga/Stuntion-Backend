package com.killjoy.model.expert

import com.google.gson.annotations.SerializedName

data class ExpertBody(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("experience_year")
    val experienceYear: Int,

    @field:SerializedName("str_number")
    val strNumber: String,

    @field:SerializedName("fee")
    val fee: Int,

    @field:SerializedName("categories")
    val categories: List<String>,

    @field:SerializedName("workplaces")
    val workplaces: List<String>,

    @field:SerializedName("educations")
    val educations: List<String>,
)
