package com.killjoy.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("isError")
    val isError: Boolean = true,

    @SerializedName("status")
    val status: String = "",

    @SerializedName("message")
    val message: String = "",

    @SerializedName("data")
    val data: T?
)