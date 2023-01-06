package com.stephenleedev.neighborandroid.remote.service.auth

import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthResponse
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel
import com.stephenleedev.neighborandroid.domain.model.auth.register.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Written by StephenLeeDev on 2022/12/21.
 */

interface AuthService {

    /**
     * /auth/social
     * 소셜 회원가입 유무 판별
     */
    @GET("/v3/46cb54e0-6315-431f-81f6-be0344334331")
    suspend fun getIsSocialAccountExist(
        @Query("socialType") socialType: String,
        @Query("socialToken") socialToken: String,
    ): ResponseBody

    /**
     * /auth/register/purpose
     * 가입목적 선택 목록
     */
    @GET("/v3/fca65d75-e2f2-4120-b08c-9c4702d51633")
    suspend fun getSignUpPurposeList(): List<SignUpPurposeModel>

    /**
     * /auth/register
     * 회원가입
     */
    @POST("/v3/9ad213b9-7f87-4aee-9729-0ef8015da180")
    suspend fun postAuthRegister(
        @Body body: RegisterRequest
    ): SocialAuthResponse

}