package com.stephenleedev.neighborandroid.domain.model.request.post


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.model.user.UserModel

data class RequestApplicationModel(
    @SerializedName("client")
    @Expose
    val client: UserModel,
    @SerializedName("request")
    @Expose
    val request: RequestModel,
    @SerializedName("isAccepted")
    @Expose
    val isAccepted: Boolean
)