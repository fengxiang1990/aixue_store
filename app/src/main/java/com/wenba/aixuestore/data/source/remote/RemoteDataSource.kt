package com.wenba.aixuestore.data.source.remote

import com.wenba.aixuestore.data.source.AppDataSource
import com.wenba.aixuestore.data.source.BaseAppInfo
import com.wenba.aixuestore.network.BaseResponse
import com.wenba.aixuestore.network.OkHttpKotlinHelper
import com.wenba.aixuestore.util.JsonWrapper
import com.wenba.aixuestore.util.UrlMapping
import com.google.gson.reflect.TypeToken
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.FormBody
import okhttp3.Request
import org.json.JSONObject


class RemoteDataSource : AppDataSource {

    val tag = "RemoteDataSource"

    override fun loadAppDetail(aKey: String, _api_key: String): Flowable<JSONObject> {
        return Flowable.just(UrlMapping.APP_DETAIL)
                .subscribeOn(Schedulers.io())
                .flatMap({ url ->
                    val formBody = FormBody.Builder()
                            .add("aKey", aKey)
                            .add("_api_key", _api_key)
                            .build()
                    var request = Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build()
                    val response = OkHttpKotlinHelper.getOkHttpClient().newCall(request).execute()
                    Flowable.just(response)
                })
                .flatMap({ response ->
                    val result = response.body()!!.string()
                    Flowable.just(JSONObject(result))
                })
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun loadAppInfos(ukey: String, _api_key: String): Flowable<BaseResponse<BaseAppInfo>> {
        return Flowable.just(UrlMapping.ListMyPublished)
                .subscribeOn(Schedulers.io())
                .flatMap({ url ->
                    val formBody = FormBody.Builder()
                            .add("uKey", ukey)
                            .add("_api_key", _api_key)
                            .build()
                    var request = Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build()
                    val response = OkHttpKotlinHelper.getOkHttpClient().newCall(request).execute()
                    Flowable.just(response)
                })
                .flatMap({ response ->
                    val result = response.body()!!.string()
                    Flowable.just(JsonWrapper.parse<BaseResponse<BaseAppInfo>>(result, object : TypeToken<BaseResponse<BaseAppInfo>>() {}.type))
                })
                .observeOn(AndroidSchedulers.mainThread())
    }

}