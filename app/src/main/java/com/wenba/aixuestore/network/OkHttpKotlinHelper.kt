package com.wenba.aixuestore.network

import okhttp3.OkHttpClient

object OkHttpKotlinHelper {

    private var client: OkHttpClient? = null

    @JvmStatic
    fun getOkHttpClient(): OkHttpClient {
        if (client == null) {
            client = OkHttpClient()
        }
        return client!!
    }
}