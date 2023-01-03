package com.stephenleedev.neighborandroid.util.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener
import com.stephenleedev.neighborandroid.R
import com.stephenleedev.neighborandroid.util.dialog.BaseDialog

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

class PermissionUtil {

    // Permission list that necessary to access storage and camera
    private fun getStoragePermissionList(): List<String> {
        val permissionList = arrayListOf<String>()
        permissionList.add(Manifest.permission.CAMERA)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            permissionList.add(Manifest.permission.READ_MEDIA_IMAGES)
        }

        return permissionList
    }

    // A function that returns the result of whether all permissions have been granted
    fun areAllPermissionsGranted(context: Context, permissionList: List<String>): Boolean {
        var allGranted = true

        permissionList.forEach {
            if (ContextCompat.checkSelfPermission(
                    context,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ) allGranted = false
        }

        return allGranted
    }

    fun checkGrantStoragePermission(activity: AppCompatActivity, positiveListener: () -> Unit) {
        val permissions = getStoragePermissionList()

        Dexter.withContext(activity)
            .withPermissions(permissions)
            .withListener(object : BaseMultiplePermissionsListener() {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (!report.areAllPermissionsGranted()) {
                            BaseDialog(
                                content = activity.getString(R.string.allow_permission_warning_message),
                                buttonPositiveContent = activity.getString(R.string.allow_permission),
                                buttonCancelContent = activity.getString(R.string.close),
                                onPositiveClick =
                                {
                                    checkGrantStoragePermission(activity, positiveListener)
                                    positiveListener()
                                }
                            ).apply {
                                isCancelable = false
                                show(activity.supportFragmentManager, null)
                            }
                        } else {
                            positiveListener()
                        }
                    }
                }
            }).check()
    }

    // A basic function to request grant permission
    private fun checkPermission(
        context: Context,
        permissions: MutableList<String>,
        positiveListener: () -> Unit,
        negativeListener: () -> Unit,
    ) {
        Dexter.withContext(context)
            .withPermissions(permissions)
            .withListener(object : BaseMultiplePermissionsListener() {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    if (!multiplePermissionsReport.areAllPermissionsGranted()) {
                        negativeListener()
                    } else {
                        positiveListener()
                    }
                }
            })
            .check()
    }

}