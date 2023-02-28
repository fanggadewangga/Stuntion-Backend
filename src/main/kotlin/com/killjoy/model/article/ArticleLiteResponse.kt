package com.killjoy.model.article

import com.google.gson.annotations.SerializedName

data class ArticleLiteResponse(
    @field:SerializedName("article_id")
    val articleId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("video_url")
    val videoUrl: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("categories")
    val categories: List<String>,
)
