package com.stephenleedev.neighborandroid.util.extension

import android.content.res.Resources
import java.text.DecimalFormat

/**
 * Written by StephenLeeDev on 2022/12/25.
 */

val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.addCommas: String
    get() = DecimalFormat("#,###,###,###,###").format(this)