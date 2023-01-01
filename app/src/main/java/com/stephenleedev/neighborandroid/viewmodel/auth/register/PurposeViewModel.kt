package com.stephenleedev.neighborandroid.viewmodel.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenleedev.neighborandroid.domain.model.auth.register.SignUpPurposeState
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetSignUpPurposeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

@HiltViewModel
class PurposeViewModel @Inject constructor(
    private val getSignUpPurposeListUseCase: GetSignUpPurposeListUseCase
) : ViewModel() {

    /**
     * Sign-up purpose list
     */
    private val _signUpPurposeState = MutableLiveData<SignUpPurposeState>(SignUpPurposeState.Loading)
    val signUpPurposeState = _signUpPurposeState as LiveData<SignUpPurposeState>

    private fun setSignUpPurposeState(value: SignUpPurposeState) {
        _signUpPurposeState.value = value
    }

    fun getSignUpPurposeList() {
        viewModelScope.launch {
            getSignUpPurposeListUseCase.execute()
                .catch {
                    setSignUpPurposeState(SignUpPurposeState.Fail)
                }
                .collect { list ->
                    setSignUpPurposeState(SignUpPurposeState.Success(list))
                }
        }
    }

}