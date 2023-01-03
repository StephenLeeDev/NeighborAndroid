package com.stephenleedev.neighborandroid.remote.service.auth

import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel
import okhttp3.ResponseBody
import retrofit2.http.GET
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

}