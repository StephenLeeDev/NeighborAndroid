package com.stephenleedev.neighborandroid.domain.model.request


import com.google.gson.annotations.SerializedName
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel
import com.stephenleedev.neighborandroid.domain.model.common.LocationModel
import com.stephenleedev.neighborandroid.domain.model.user.UserModel

data class RequestModel(
    @SerializedName("address")
    val address: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: LocationModel,
    @SerializedName("message")
    val message: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("request")
    val request: SignUpPurposeModel,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user")
    val userModel: UserModel
)