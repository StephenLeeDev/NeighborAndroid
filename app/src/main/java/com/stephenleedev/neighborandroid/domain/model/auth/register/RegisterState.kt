package com.stephenleedev.neighborandroid.domain.model.auth.register

import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthResponse

/**
 * Written by StephenLeeDev on 2023/01/03.
 */

sealed class RegisterState {
    object Loading : RegisterState()
    object Fail : RegisterState()
    data class Success(val socialAuthResponse: SocialAuthResponse) : RegisterState()
}
