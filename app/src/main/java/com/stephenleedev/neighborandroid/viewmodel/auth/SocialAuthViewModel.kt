package com.stephenleedev.neighborandroid.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthRequest
import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthState
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetIsSocialAccountExistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

@HiltViewModel
class SocialAuthViewModel @Inject constructor(
    private val getIsSocialAccountExistUseCase: GetIsSocialAccountExistUseCase,
) : ViewModel() {

    /**
     * Check Holding Token Status
     *
     * Empty -> Go to SignIn Screen
     * Success -> Go to Main Screen
     */
    private val _socialAuthState = MutableLiveData<SocialAuthState>(SocialAuthState.Loading)
    val socialAuthState = _socialAuthState as LiveData<SocialAuthState>

    private fun setSocialAuthState(value: SocialAuthState) {
        _socialAuthState.value = value
    }

    fun getIsSocialAccountExist(body: SocialAuthRequest) {
        viewModelScope.launch {
            getIsSocialAccountExistUseCase.execute(body = body)
                .catch { error ->
                    // Account already exist
                    // Proceed sign-in process
                    if (error is HttpException && error.code() == 409) {
                        setSocialAuthState(SocialAuthState.Exist)
                    }
                }
                .collect {
                    // Account doesn't exist
                    // Proceed sign-up process
                    setSocialAuthState(SocialAuthState.None)
                }
        }
    }

}