package com.stephenleedev.neighborandroid.remote.service.auth

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Written by StephenLeeDev on 2022/12/21.
 */

interface AuthService {

    // 소셜 회원가입 유무 판별
    @GET("/v3/46cb54e0-6315-431f-81f6-be0344334331")
    suspend fun getIsSocialAccountExist(
        @Query("socialType") socialType: String,
        @Query("socialToken") socialToken: String,
    ): ResponseBody

}