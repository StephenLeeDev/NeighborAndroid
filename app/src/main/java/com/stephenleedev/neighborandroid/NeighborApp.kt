package com.stephenleedev.neighborandroid

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.naver.maps.map.NaverMapSdk
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
        initNaverMap()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initNaverMap() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_MAP_API_KEY)
    }

    private fun initKakao() {
//        val keyHash = Utility.getKeyHash(this)
//        logFunctions("keyHash : $keyHash")

        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
    }

}