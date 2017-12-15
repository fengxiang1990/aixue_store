package com.wenba.aixuestore.apps

import android.util.Log
import com.wenba.ailearn.lib.extentions.toBean
import com.wenba.aixuestore.data.AppInfoDetail
import com.wenba.aixuestore.data.source.AppDataRepostory
import com.wenba.aixuestore.util.Config

class AppDetailPressenter(appDataRepostory: AppDataRepostory, appView: AppContract.DetailView) : AppContract.DetailPressenter {

    val tag = "AppDetailPressenter"

    private var mAppView: AppContract.DetailView = checkNotNull(appView)

    private var mTasksRepository: AppDataRepostory = checkNotNull(appDataRepostory)

    override fun start() {
    }

    override fun loadAppDetail(aKey: String) {
        mTasksRepository.loadAppDetail(aKey, Config._api_key)
                ?.subscribe({ jsonObj ->
                    val appInfoDetail = jsonObj.toString().toBean(AppInfoDetail::class.java)
                    mAppView.showAppDetail(appInfoDetail)
                }, { e ->
                    e.printStackTrace()
                }, {
                    Log.d(tag, "loadAppDetail complete")
                }
                )
    }

}