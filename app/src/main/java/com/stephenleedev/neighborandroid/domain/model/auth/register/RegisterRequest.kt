package com.stephenleedev.neighborandroid.domain.model.auth.register


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("apartmentId")
    val apartmentId: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("purposeIds")
    val purposeIds: List<Int>,
    @SerializedName("socialToken")
    val socialToken: String,
    @SerializedName("socialType")
    val socialType: String
)