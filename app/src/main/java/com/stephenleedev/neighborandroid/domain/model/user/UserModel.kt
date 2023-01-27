package com.stephenleedev.neighborandroid.domain.model.user


import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("apartmentId")
    val apartmentId: Int,
    @SerializedName("apartmentName")
    val apartmentName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)