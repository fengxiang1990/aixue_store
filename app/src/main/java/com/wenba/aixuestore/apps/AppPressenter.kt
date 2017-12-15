package com.wenba.aixuestore.apps

import android.content.Context
import android.util.Log
import com.wenba.ailearn.lib.extentions.NetWorkUtils
import com.wenba.aixuestore.data.AppInfo
import com.wenba.aixuestore.data.source.AppDataRepostory
import com.wenba.aixuestore.util.Config
import io.reactivex.Flowable

class AppPressenter(context: Context, appDataRepostory: AppDataRepostory, appView: AppContract.View) : AppContract.Pressenter {


    val tag = this.javaClass.simpleName

    val TYPE_ANDROID = 2

    private var mContext: Context = checkNotNull(context)

    private var mAppView: AppContract.View = checkNotNull(appView)

    private var mTasksRepository: AppDataRepostory = checkNotNull(appDataRepostory)


    override fun loadAppInfos(filter: Filter, page: Int) {
        Log.d(tag, "page-->" + page)
        if (!NetWorkUtils.checkNetWork(mContext)) {
            mAppView.showNetError()
            mAppView.onLoadComplete()
            return
        }
        mTasksRepository.loadAppInfos(Config.uKey, Config._api_key, page)
                ?.subscribe({ response ->
                    val baseInfo = response.data
                    if (baseInfo == null) {
                        mAppView.showApps(ArrayList())
                        mAppView.onLoadComplete()
                        return@subscribe
                    }
                    when (filter) {
                        Filter.ALL -> mAppView.showApps(Flowable.fromIterable(baseInfo!!.list)
                                .filter({ t: AppInfo ->
                                    t.appType?.toInt() == TYPE_ANDROID
                                })
                                .toList().blockingGet())
                        Filter.MASTER -> mAppView.showApps(Flowable.fromIterable(baseInfo!!.list)
                                .filter({ t: AppInfo ->
                                    !t.appIdentifier!!.contains(".pro")
                                            && t.appType?.toInt() == TYPE_ANDROID
                                            && !t.appIdentifier!!.contains("aixuestore")
                                })
                                .toList().blockingGet())
                        Filter.PRO -> mAppView.showApps(Flowable.fromIterable(baseInfo!!.list)
                                .filter({ t: AppInfo ->
                                    t.appIdentifier!!.contains(".pro")
                                            && t.appType?.toInt() == TYPE_ANDROID
                                            && !t.appIdentifier!!.contains("aixuestore")
                                })
                                .toList().blockingGet())
                    }
                    mAppView.onLoadComplete()
                })
    }


    override fun start() {
        loadAppInfos()
    }


}