package com.killjoy.model.question

import com.google.gson.annotations.SerializedName

data class QuestionExpertAnswerBody(
    @field:SerializedName("expert_id")
    val expertId: String,

    @field:SerializedName("answer")
    val answer: String,
)