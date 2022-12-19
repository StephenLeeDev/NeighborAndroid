package com.stephenleedev.neighborandroid.remote.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.stephenleedev.neighborandroid.BuildConfig
import com.stephenleedev.neighborandroid.domain.util.PrefUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Written by StephenLeeDev on 2022/12/18.
 */

// TODO : Refactor prefUtil to use cases
class TokenInterceptor(private val prefUtil: PrefUtil) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
        try {
            if (prefUtil.getUserAccessToken().isNotEmpty()) {
                builder.addHeader(
                    "Authorization",
                    "Bearer ${prefUtil.getUserAccessToken()}"
                )
//                logFunctions("accessToken : ${prefUtil.getUserAccessToken()}")
            }
        } catch (ignored: Exception) {
        }
        val request: Request = builder.build()
        val response = chain.proceed(request)

        return if (response.code == 401) {
            if (getNewToken()) {
                response.close()
                builder.removeHeader("authorization")
                    .addHeader("authorization", prefUtil.getUserAccessToken())
                chain.proceed(builder.build())
            }
            else response
        } else {
            response
        }
    }

    private fun getNewToken(): Boolean {
        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url("${BuildConfig.SERVER_URL}/auth/refresh")
            .addHeader("Authorization", "Bearer ${prefUtil.getRefreshToken()}")
            .build()
        try {
            val response = client.newCall(request).execute()
            return if (response.isSuccessful) {

                // TODO : Refactor json data to authentication response model
                val json = response.body?.string()
                val data = Gson().fromJson(json, JsonObject::class.java)

                prefUtil.setUserAccessToken(data["accessToken"].asString)
                prefUtil.setRefreshToken(data["refreshToken"].asString)
                true
            } else {
                false
            }
        } catch (e: Exception) {
        }
        return false
    }
}