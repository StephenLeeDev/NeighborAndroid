package com.stephenleedev.neighborandroid.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenleedev.neighborandroid.domain.model.splash.SplashState
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetUserAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserAccessTokenUseCase: GetUserAccessTokenUseCase
) : ViewModel() {

    /**
     * Check Holding Token Status
     *
     * Empty -> Go to SignIn Screen
     * Success -> Go to Main Screen
     */
    private val _splashState = MutableLiveData<SplashState>(SplashState.Loading)
    val splashState = _splashState as LiveData<SplashState>

    private fun setSplashState(value: SplashState) {
        _splashState.value = value
    }

    fun getUserAccessToken() {
        viewModelScope.launch {
            val token = getUserAccessTokenUseCase.execute()

            when {
                token.isEmpty() -> setSplashState(SplashState.Empty)
                else -> setSplashState(SplashState.Success(token))
            }
        }
    }

}