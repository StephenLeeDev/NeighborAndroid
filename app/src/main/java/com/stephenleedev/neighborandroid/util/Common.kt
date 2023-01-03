package com.stephenleedev.neighborandroid.util

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.stephenleedev.neighborandroid.BuildConfig

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

fun logFunctions(msg: String) {
    if (BuildConfig.DEBUG) Log.e("logFunctions", msg)
}

fun showListAlertDialog(
    context: Context,
    title: String,
    list: Array<String>,
    onClickListener: DialogInterface.OnClickListener,
    isCancelable: Boolean = true
) {
    val builder = AlertDialog.Builder(context)

    builder
        .setTitle(title)
        .setItems(list, onClickListener)
        .setCancelable(isCancelable)

    builder.show()
}