package com.stephenleedev.neighborandroid.domain.model.request.post

import com.google.gson.annotations.SerializedName
import com.stephenleedev.neighborandroid.domain.model.chat.ChatMessageModel
import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.model.user.UserModel

data class RequestApplicationModel(
    @SerializedName("client")
    val client: UserModel,
    @SerializedName("request")
    val request: RequestModel,
    @SerializedName("lastMessage")
    val lastMessage: ChatMessageModel,
    @SerializedName("isAccepted")
    val isAccepted: Boolean,
    @SerializedName("mapThumbnail")
    val mapThumbnail: String
)