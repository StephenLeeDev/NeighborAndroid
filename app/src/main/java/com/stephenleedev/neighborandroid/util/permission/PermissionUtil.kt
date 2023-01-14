package com.stephenleedev.neighborandroid.util.permission

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
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

    // Permission list that necessary to access location
    private fun getLocationPermissionList(): List<String> {
        val permissionList = arrayListOf<String>()
        permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        return permissionList
    }

    fun checkGrantLocationPermission(activity: AppCompatActivity, positiveListener: () -> Unit) {
        val permissions = getLocationPermissionList()

        Dexter.withContext(activity)
            .withPermissions(permissions)
            .withListener(object : BaseMultiplePermissionsListener() {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (!report.areAllPermissionsGranted()) {
                            BaseDialog(
                                content = activity.getString(R.string.allow_location_permission_warning_message),
                                buttonPositiveContent = activity.getString(R.string.allow_permission),
                                buttonCancelContent = activity.getString(R.string.close),
                                onPositiveClick =
                                {
                                    checkGrantLocationPermission(activity, positiveListener)
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

}