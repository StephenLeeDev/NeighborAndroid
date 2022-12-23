package com.stephenleedev.neighborandroid.domain.model.splash

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

sealed class SplashState {
    object Loading : SplashState()
    object Empty : SplashState()
    data class Success(val accessToken: String) : SplashState()
}
