package com.stephenleedev.neighborandroid.domain.model.chat


import com.google.gson.annotations.SerializedName
import com.stephenleedev.neighborandroid.util.date.DateUtil

data class ChatMessageModel(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("message")
    val message: String
) {
    fun getCreatedAtString(): String = DateUtil().getIntervalTimeByString(createdAt)
}