package com.stephenleedev.neighborandroid.remote.service.user

import com.stephenleedev.neighborandroid.domain.model.user.thumbnail.PatchUserThumbnailResponse
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

interface UserService {

    /**
     * /user/thumbnail
     * 프로필 썸네일 이미지 수정
     */
    @Multipart
    @PATCH("/v3/818662cb-3408-4499-a81a-f57186ebe0d8")
    suspend fun patchUserThumbnail(
        @Part part: MultipartBody.Part
    ): PatchUserThumbnailResponse

}