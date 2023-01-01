package com.stephenleedev.neighborandroid.domain.model.auth.register

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

sealed class SignUpPurposeState {
    object Loading : SignUpPurposeState()
    object Fail : SignUpPurposeState()
    data class Success(val list: List<SignUpPurposeModel>) : SignUpPurposeState()
}