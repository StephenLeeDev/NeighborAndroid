package com.stephenleedev.neighborandroid.util.file

import android.content.Context
import android.net.Uri
import android.os.Build
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

class FileUtil {

    private fun getPathFromUri(context: Context, uri: Uri): String {
        val cursor = context.contentResolver?.query(uri, null, null, null, null)
        cursor?.moveToNext()
        val path: String = cursor?.getString(cursor.getColumnIndexOrThrow("_data")) ?: ""
        cursor?.close()
        return path
    }

    fun getMultipartFromUri(context: Context, uri: Uri): MultipartBody.Part {
        val file =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) File(
                getPathFromUri(
                    context,
                    uri
                )
            )
            else File(uri.path ?: "")
        val pos: Int = file.name.lastIndexOf(".")
        val ext = file.name.substring(pos + 1)
        val fileBody = file.asRequestBody("image/$ext".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData(
            "imageFiles",
            file.name,
            fileBody
        )
    }

}