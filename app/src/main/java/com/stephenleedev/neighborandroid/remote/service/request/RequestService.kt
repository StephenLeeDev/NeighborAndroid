package com.stephenleedev.neighborandroid.remote.service.request

import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplyResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

interface RequestService {

    /**
     * /requests
     *
     * Nearby request list
     * 심부름 요청 목록
     */
    @GET("/v3/97cc9d55-2108-456d-b20f-b361e1fad2b3")
    suspend fun getRequestList(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): List<RequestModel>

    /**
     * /requests/apply
     *
     * Apply to request
     * 심부름 수락 요청
     */
    @POST("/v3/5df5d3bf-343c-4f57-b81c-cf9dbcb3f452")
    suspend fun postApplyToRequest(
        @Query("id") id: Int // Request Item's ID
    ): RequestApplyResponse

}