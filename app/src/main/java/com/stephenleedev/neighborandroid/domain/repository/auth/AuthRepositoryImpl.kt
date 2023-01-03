package com.stephenleedev.neighborandroid.domain.repository.auth

import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthRequest
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel
import com.stephenleedev.neighborandroid.domain.util.PrefUtil
import com.stephenleedev.neighborandroid.remote.service.auth.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody

/**
 * Written by StephenLeeDev on 2022/12/21.
 */

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val prefUtil: PrefUtil
) : AuthRepository {

    override suspend fun getUserAccessToken(): String {
        return prefUtil.getUserAccessToken()
    }

    override suspend fun getRefreshToken(): String {
        return prefUtil.getRefreshToken()
    }

    override suspend fun setUserAccessToken(userAccessToken: String) {
        prefUtil.setUserAccessToken(userAccessToken)
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        prefUtil.setRefreshToken(refreshToken)
    }

    override suspend fun getIsSocialAccountExist(body: SocialAuthRequest): Flow<ResponseBody> {
        return flow { emit(authService.getIsSocialAccountExist(socialType = body.socialType, socialToken = body.socialToken)) }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSignUpPurposeList(): Flow<List<SignUpPurposeModel>> {
        return flow { emit(authService.getSignUpPurposeList()) }.flowOn(Dispatchers.IO)
    }

}