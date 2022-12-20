package com.stephenleedev.neighborandroid.domain.model

/**
 * Written by StephenLeeDev on 2022/12/20.
 */

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)