package com.stephenleedev.neighborandroid.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Written by StephenLeeDev on 2022/12/20.
 */

data class SocialAuthRequest(
    @SerializedName("socialType")
    val socialType: String = "kakao", // TODO : Create socialType enum class and refactor
    @SerializedName("socialToken")
    val socialToken: String,
)
