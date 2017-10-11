package com.wenba.aixuestore.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {

    private val sharedName = "com.wenba.aixue"
    private val mode = Context.MODE_APPEND

    @JvmStatic
    private fun getSharedPreferences(context: Context?): SharedPreferences? {
        return context?.getSharedPreferences(sharedName, mode)
    }

    @JvmStatic
    fun save(context: Context?, key: String?, value: Any?) {
        val editor = getSharedPreferences(context)?.edit()
        when (value) {
            is String -> editor?.putString(key, value)
            is Boolean -> editor?.putBoolean(key, value)
            is Int -> editor?.putInt(key, value)
            is Long -> editor?.putLong(key, value)
            is Float -> editor?.putFloat(key, value)
        }
        editor?.apply()
    }

    @JvmStatic
    fun getString(context: Context?, key: String): String? {
        return getSharedPreferences(context)?.getString(key, null)
    }

    @JvmStatic
    fun getBoolean(context: Context?, key: String): Boolean? {
        return getSharedPreferences(context)?.getBoolean(key, false)
    }

    @JvmStatic
    fun getLong(context: Context?, key: String): Long? {
        return getSharedPreferences(context)?.getLong(key, -1)
    }

    @JvmStatic
    fun getFloat(context: Context?, key: String): Float? {
        return getSharedPreferences(context)?.getFloat(key, -1.0f)
    }

    @JvmStatic
    fun getInt(context: Context?, key: String): Int? {
        return getSharedPreferences(context)?.getInt(key, -1)
    }

    @JvmStatic
    fun getInt(context: Context?, key: String, default: Int): Int? {
        return getSharedPreferences(context)?.getInt(key, default)
    }
}