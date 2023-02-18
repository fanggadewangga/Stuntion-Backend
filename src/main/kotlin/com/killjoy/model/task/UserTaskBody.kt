package com.killjoy.model.task

import com.google.gson.annotations.SerializedName

data class UserTaskBody(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("task_id")
    val taskId: String
)
