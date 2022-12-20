package com.stephenleedev.neighborandroid.domain.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Written by StephenLeeDev on 2022/12/18.
 */

class PrefUtil(context : Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            "neighbor-pref",
            Context.MODE_PRIVATE
        )
    }

    fun setUserAccessToken(token: String) {
        sharedPreferences.edit().putString("accessToken", token).apply()
    }

    fun getUserAccessToken(): String =
        sharedPreferences.getString("accessToken", "") ?: ""

    fun setRefreshToken(token: String) {
        sharedPreferences.edit().putString("refreshToken", token).apply()
    }

    fun getRefreshToken(): String =
        sharedPreferences.getString("refreshToken", "") ?: ""

    fun isHoldingToken(): Boolean =
        getUserAccessToken().isNotEmpty()

    fun logout() {
        setUserAccessToken("")
        setRefreshToken("")
    }

}