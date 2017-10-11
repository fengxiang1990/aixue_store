package com.wenba.aixuestore.apps

import com.wenba.aixuestore.data.AppInfo
import com.wenba.aixuestore.data.source.AppDataRepostory
import com.wenba.aixuestore.util.Config
import io.reactivex.Flowable

class AppPressenter(appDataRepostory: AppDataRepostory, appView: AppContract.View) : AppContract.Pressenter {


    val TYPE_ANDROID = 2

    private var mAppView: AppContract.View = checkNotNull(appView)

    private var mTasksRepository: AppDataRepostory = checkNotNull(appDataRepostory)


    override fun loadAppInfos(filter: Filter) {
        mAppView.showRefresh(true)
        mTasksRepository.loadAppInfos(Config.uKey, Config._api_key)
                ?.subscribe({ response ->
                    val baseInfo = response.data
                    if (baseInfo == null) {
                        mAppView.showApps(ArrayList())
                        mAppView.showRefresh(false)
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
                    mAppView.showRefresh(false)
                })
    }


    override fun start() {
        loadAppInfos()
    }
}