package com.syed.humantask.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings.Secure
import com.syed.humantask.BuildConfig

object Utility {

    fun getBaseUrl() : String {
        return BuildConfig.BASE_URL
    }

    fun getDeviceOS() : String {
        val baseOs = Build.VERSION.BASE_OS
        //val sdkVersion = Build.VERSION.SDK_INT
        //val codeName = Build.VERSION.CODENAME
        //val versionRelease = Build.VERSION.RELEASE
        return baseOs
    }

    @SuppressLint("HardwareIds")
    fun getDeviceUniqueId(context: Context?): String {
        return context?.let {
            Secure.getString(
                it.contentResolver,
                Secure.ANDROID_ID
            )
        } ?:  ""
    }


}