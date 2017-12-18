package com.wenba.aixuestore.network

import android.content.Context
import android.util.Log
import com.wenba.ailearn.lib.extentions.NetWorkUtils
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.internal.operators.flowable.FlowableJust
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.net.ProxySelector

object OkHttpKotlinHelper {

    private val tag = "OkHttpKotlinHelper"

    private var client: OkHttpClient? = null

    private var context: Context? = null

    @JvmStatic
    fun init(context: Context) {
        OkHttpKotlinHelper.context = context
    }

    @JvmStatic
    private fun getOkHttpClient(): OkHttpClient {
        if (client == null) {
            client = OkHttpClient.Builder()
                    .retryOnConnectionFailure(false)
                    .proxySelector(ProxySelector.getDefault())
                    //.addInterceptor(NetConnInterceptor())
                    .addNetworkInterceptor(LogInterceptor())
                    .build()
        }
        return client!!
    }

    class NetConnInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            Log.e(tag, "NetConnInterceptor in")
            val request = chain?.request()
            return if (!NetWorkUtils.checkNetWork(context)) {
                Log.e(tag, "NetConnInterceptor no network")
                throw NotHaveNetworkException()
            } else {
                Log.e(tag, "NetConnInterceptor  has network,proceed net request")
                chain?.proceed(request)!!
            }
        }
    }

    class LogInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            val request = chain?.request()
            val response = chain?.proceed(request)
            Log.d(tag, "request->" + request.toString())
            Log.d(tag, "response->" + response.toString())
            return response!!
        }
    }

    private fun execute(url: String, params: Map<String, Any>): Response {
        val keys = params.keys
        val formBody = FormBody.Builder()
        for (key in keys) {
            formBody.add(key, params[key] as String?)
        }
        var request = Request.Builder()
                .url(url)
                .post(formBody.build())
                .build()
        return OkHttpKotlinHelper.getOkHttpClient().newCall(request).execute()
    }

    fun postFormSync(url: String, params: Map<String, Any>): Flowable<Response> {
        return FlowableJust.just(null).map {
            if (!NetWorkUtils.checkNetWork(context)) {
                Log.e(tag, "NetConnInterceptor no network")
                throw NotHaveNetworkException()
            }
            execute(url, params)
        }.subscribeOn(Schedulers.io())
    }
}