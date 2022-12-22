package com.stephenleedev.neighborandroid

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.stephenleedev.neighborandroid.util.logFunctions
import dagger.hilt.android.HiltAndroidApp

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

@HiltAndroidApp
class NeighborApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKakao()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initKakao() {
//        val keyHash = Utility.getKeyHash(this)
//        logFunctions("keyHash : $keyHash")

        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
    }

}