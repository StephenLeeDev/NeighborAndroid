package com.stephenleedev.neighborandroid.util.extension

import android.content.res.Resources

/**
 * Written by StephenLeeDev on 2022/12/25.
 */

val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()