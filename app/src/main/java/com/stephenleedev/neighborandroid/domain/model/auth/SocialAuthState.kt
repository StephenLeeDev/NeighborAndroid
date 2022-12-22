package com.stephenleedev.neighborandroid.domain.model.auth

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

sealed class SocialAuthState {
    object Loading : SocialAuthState()
    object None : SocialAuthState()
    object Exist : SocialAuthState()
}