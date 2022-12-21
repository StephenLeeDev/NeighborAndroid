package com.stephenleedev.neighborandroid.domain.model.auth

import com.google.gson.annotations.SerializedName

/**
 * Written by StephenLeeDev on 2022/12/20.
 */

data class SocialAuthRequest(
    @SerializedName("socialType")
    val socialType: String,
    @SerializedName("socialToken")
    val socialToken: String,
)
