package com.killjoy.model.expert

import com.google.gson.annotations.SerializedName

data class ExpertResponse(

    @field:SerializedName("expert_id")
    val expertId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("experience_year")
    val experienceYear: Int,

    @field:SerializedName("str")
    val str: String,

    @field:SerializedName("fee")
    val fee: Int,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("category")
    val category: List<String>,

    @field:SerializedName("workplace")
    val workplace: List<String>,
)