package com.stephenleedev.neighborandroid.remote.service.request

import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

interface RequestService {

    /**
     * /requests
     * 심부름 요청 목록
     */
    @GET("/v3/6e6e0eae-9186-4126-a991-d340cf5342f6")
    suspend fun getRequestList(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): List<RequestModel>

}