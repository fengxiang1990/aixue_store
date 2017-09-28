package com.wenba.aixuestore.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {

    private val sharedName = "com.wenba.aixue"
    private val mode = Context.MODE_APPEND

    @JvmStatic
    fun getSharedPreferences(context: Context?): SharedPreferences? {
        return context?.getSharedPreferences(sharedName, mode)
    }
}