package com.wenba.aixuestore.apps

import com.wenba.aixuestore.BasePresenter
import com.wenba.aixuestore.BaseView
import com.wenba.aixuestore.data.AppInfo
import com.wenba.aixuestore.data.AppInfoDetail

interface AppContract {

    interface View : BaseView<Pressenter> {

        fun showFilteringPopUpMenu()
        fun showApps(appinfos: List<AppInfo>?)
        fun toAppDetail(appKey: String, appName: String)
        fun toInstall(aKey: String, appName: String)
        fun showNetError()
        fun onLoadComplete()
    }

    interface DetailView : BaseView<Pressenter> {
        fun showAppDetail(appInfoDetail: AppInfoDetail?)
        fun toInstall(aKey: String, appName: String)
    }

    interface DetailPressenter : BasePresenter {
        fun loadAppDetail(aKey: String)
    }

    interface Pressenter : BasePresenter {

        fun loadAppInfos(filter: Filter = Filter.ALL, page: Int = 1)

    }
}