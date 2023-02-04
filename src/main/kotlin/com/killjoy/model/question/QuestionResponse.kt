package com.killjoy.model.question

import com.google.gson.annotations.SerializedName

data class QuestionResponse(

    @field:SerializedName("question_id")
    val questionId: String,

    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("expert_id")
    val expertId: String?,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("question")
    val question: String,

    @field:SerializedName("answer")
    val answer: String?,

    @field:SerializedName("isAnonymous")
    val isAnonymous: Boolean,

    @field:SerializedName("timestamp")
    val timestamp: String,

    @field:SerializedName("category")
    val category: List<String>,

)
