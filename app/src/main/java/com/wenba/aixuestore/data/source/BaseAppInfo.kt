package com.wenba.aixuestore.data.source

import com.wenba.aixuestore.data.AppInfo

class BaseAppInfo {

    var list: List<AppInfo>? = null
    override fun toString(): String {
        return "BaseAppInfo(list=$list)"
    }

}