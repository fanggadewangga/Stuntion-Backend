package com.killjoy.model.article

import com.google.gson.annotations.SerializedName

data class ArticleBody(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("reviewer")
    val reviewer: String,

)
