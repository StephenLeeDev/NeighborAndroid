package com.stephenleedev.neighborandroid.domain.repository.auth

import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthRequest
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

/**
 * Written by StephenLeeDev on 2022/12/21.
 */

interface AuthRepository {
    suspend fun getUserAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun setUserAccessToken(userAccessToken: String)
    suspend fun setRefreshToken(refreshToken: String)
    suspend fun getIsSocialAccountExist(body: SocialAuthRequest): Flow<ResponseBody>
    suspend fun getSignUpPurposeList(): Flow<List<SignUpPurposeModel>>
}