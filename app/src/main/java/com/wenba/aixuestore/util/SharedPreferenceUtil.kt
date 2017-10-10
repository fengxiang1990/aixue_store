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

    @JvmStatic
    fun save(context: Context?, key: String?, value: Any?) {
        val editor = context?.getSharedPreferences(sharedName, mode)?.edit()
        when (value) {
            value is String -> editor?.putString(key, value as String?)
            value is Boolean -> editor?.putBoolean(key, value as Boolean)
            value is Int -> editor?.putInt(key, value as Int)
            value is Long -> editor?.putLong(key, value as Long)
            value is Float -> editor?.putFloat(key, value as Float)
        }
        editor?.apply()
    }

    @JvmStatic
    fun getString(context: Context?, key: String): String? {
        return context?.getSharedPreferences(sharedName, mode)?.getString(key, null)
    }

    @JvmStatic
    fun getBoolean(context: Context?, key: String): Boolean? {
        return context?.getSharedPreferences(sharedName, mode)?.getBoolean(key, false)
    }

    @JvmStatic
    fun getLong(context: Context?, key: String): Long? {
        return context?.getSharedPreferences(sharedName, mode)?.getLong(key, -1)
    }

    @JvmStatic
    fun getFloat(context: Context?, key: String): Float? {
        return context?.getSharedPreferences(sharedName, mode)?.getFloat(key, -1.0f)
    }

    @JvmStatic
    fun getInt(context: Context?, key: String): Int? {
        return context?.getSharedPreferences(sharedName, mode)?.getInt(key, -1)
    }
}