package com.stephenleedev.neighborandroid.remote.service.apartment

import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentModel
import retrofit2.http.GET

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

interface ApartmentService {

    /**
     * /place
     * 모든 아파트 단지 목록
     */
    @GET("/v3/9f5ec8f5-1b60-4543-a5f9-6a3d356b8a27")
    suspend fun getAllApartmentList(): List<ApartmentModel>

}