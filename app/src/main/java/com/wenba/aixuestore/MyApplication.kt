package com.wenba.aixuestore

import android.app.Application
import com.wenba.aixuestore.network.OkHttpKotlinHelper

/**
 * Created by Wenba on 2017/12/18.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        OkHttpKotlinHelper.init(this)
    }
}