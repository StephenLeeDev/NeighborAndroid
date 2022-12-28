package com.stephenleedev.neighborandroid.util

import android.util.Log
import com.stephenleedev.neighborandroid.BuildConfig

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

fun logFunctions(msg: String) {
    if (BuildConfig.DEBUG) Log.e("logFunctions", msg)
}