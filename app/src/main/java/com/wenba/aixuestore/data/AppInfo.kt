package com.wenba.aixuestore.data

/**
 * 返回参数	类型	说明
appKey	String	返回应用最新build的App Key
appType	Integer	应用类型（1:iOS; 2:Android）
appFileSize	Integer	App 文件大小
appName	String	应用名称
appVersion	String	版本号
appVersionNo	Integer	适用于Android的版本编号，iOS始终为0
appBuildVersion	Integer	蒲公英生成的用于区分历史版本的build号
appIdentifier	String	应用程序包名，iOS为BundleId，Android为包名
appIcon	String	应用的Icon图标key，访问地址为 http://o1wh05aeh.qnssl.com/image/view/app_icons/[应用的Icon图标key]
"appDescription":"",
"appUpdateDescription":"",
"appScreenshots":"",
appCreated	String	应用上传时间
 */
data class AppInfo(
        var appKey: String?, var appType: String?, var appFileSize: String?,
        var appName: String?, var appVersion: String?, var appVersionNo: String?,
        var appBuildVersion: String?, var appIdentifier: String?,
        var appIcon: String?, var appDescription: String?,
        var appUpdateDescription: String?, var appScreenshots: String?,
        var appCreated: String?
) {
    override fun toString(): String {
        return "AppInfo(appKey=$appKey, appType=$appType, appFileSizee=$appFileSize, appName=$appName, appVersion=$appVersion, appVersionNo=$appVersionNo, appBuildVersion=$appBuildVersion, appIdentifier=$appIdentifier, appIcon=$appIcon, appDescription=$appDescription, appUpdateDescription=$appUpdateDescription, appScreenshots=$appScreenshots, appCreated=$appCreated)"
    }
}