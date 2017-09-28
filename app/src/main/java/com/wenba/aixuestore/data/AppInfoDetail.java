package com.wenba.aixuestore.data;

import java.util.List;

public class AppInfoDetail {

    /**
     * code : 0
     * message :
     * data : {"appKey":"9528e62069cf4f5dc84dd3261df25f06","userKey":"39b74627fbddf33a208e48610fdeee4b","appType":"2","appIsLastest":"1","appFileSize":"20107880","appName":"大闹天宫Pro","appVersion":"1.2.5","appVersionNo":"1002005","appBuildVersion":"5","appIdentifier":"com.wenba.pro.havocinheaven","appIcon":"0b4bbe19ad885bb74bed2f94bdb3d2b8","appDescription":"","appUpdateDescription":"测试包","appScreenshots":"","appShortcutUrl":"dntkpro","appCreated":"2017-09-18 20:07:37","appUpdated":"2017-09-18 20:11:31","appQRCodeURL":"http://www.pgyer.com/app/qrcodeHistory/95195821fa831e3b121808360e9471f6ded60f817c7b7df4cdacb421c117a51e","appFollowed":"0","otherapps":[{"appKey":"c777f4ffbc6a50d9c5b354c043bfa028","userKey":"39b74627fbddf33a208e48610fdeee4b","appName":"大闹天宫Pro","appVersion":"1.2.1","appBuildVersion":"4","appIdentifier":"com.wenba.pro.havocinheaven","appCreated":"2017-09-06","appUpdateDescription":""},{"appKey":"bb4c54cb2f19695f9e6c2800bc895ac1","userKey":"39b74627fbddf33a208e48610fdeee4b","appName":"大闹天宫Pro","appVersion":"1.2.1","appBuildVersion":"3","appIdentifier":"com.wenba.pro.havocinheaven","appCreated":"2017-09-05","appUpdateDescription":""},{"appKey":"8d2829951090e576fdc6a5469aa884cf","userKey":"39b74627fbddf33a208e48610fdeee4b","appName":"大闹天宫Pro","appVersion":"1.2.1","appBuildVersion":"2","appIdentifier":"com.wenba.pro.havocinheaven","appCreated":"2017-09-05","appUpdateDescription":""}],"otherAppsCount":"3","comments":[],"commentsCount":"0"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appKey : 9528e62069cf4f5dc84dd3261df25f06
         * userKey : 39b74627fbddf33a208e48610fdeee4b
         * appType : 2
         * appIsLastest : 1
         * appFileSize : 20107880
         * appName : 大闹天宫Pro
         * appVersion : 1.2.5
         * appVersionNo : 1002005
         * appBuildVersion : 5
         * appIdentifier : com.wenba.pro.havocinheaven
         * appIcon : 0b4bbe19ad885bb74bed2f94bdb3d2b8
         * appDescription :
         * appUpdateDescription : 测试包
         * appScreenshots :
         * appShortcutUrl : dntkpro
         * appCreated : 2017-09-18 20:07:37
         * appUpdated : 2017-09-18 20:11:31
         * appQRCodeURL : http://www.pgyer.com/app/qrcodeHistory/95195821fa831e3b121808360e9471f6ded60f817c7b7df4cdacb421c117a51e
         * appFollowed : 0
         * otherapps : [{"appKey":"c777f4ffbc6a50d9c5b354c043bfa028","userKey":"39b74627fbddf33a208e48610fdeee4b","appName":"大闹天宫Pro","appVersion":"1.2.1","appBuildVersion":"4","appIdentifier":"com.wenba.pro.havocinheaven","appCreated":"2017-09-06","appUpdateDescription":""},{"appKey":"bb4c54cb2f19695f9e6c2800bc895ac1","userKey":"39b74627fbddf33a208e48610fdeee4b","appName":"大闹天宫Pro","appVersion":"1.2.1","appBuildVersion":"3","appIdentifier":"com.wenba.pro.havocinheaven","appCreated":"2017-09-05","appUpdateDescription":""},{"appKey":"8d2829951090e576fdc6a5469aa884cf","userKey":"39b74627fbddf33a208e48610fdeee4b","appName":"大闹天宫Pro","appVersion":"1.2.1","appBuildVersion":"2","appIdentifier":"com.wenba.pro.havocinheaven","appCreated":"2017-09-05","appUpdateDescription":""}]
         * otherAppsCount : 3
         * comments : []
         * commentsCount : 0
         */

        private String appKey;
        private String userKey;
        private String appType;
        private String appIsLastest;
        private Long appFileSize;
        private String appName;
        private String appVersion;
        private String appVersionNo;
        private String appBuildVersion;
        private String appIdentifier;
        private String appIcon;
        private String appDescription;
        private String appUpdateDescription;
        private String appScreenshots;
        private String appShortcutUrl;
        private String appCreated;
        private String appUpdated;
        private String appQRCodeURL;
        private String appFollowed;
        private String otherAppsCount;
        private String commentsCount;
        private List<OtherappsBean> otherapps;
        private List<?> comments;

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getAppIsLastest() {
            return appIsLastest;
        }

        public void setAppIsLastest(String appIsLastest) {
            this.appIsLastest = appIsLastest;
        }

        public Long getAppFileSize() {
            return appFileSize;
        }

        public void setAppFileSize(Long appFileSize) {
            this.appFileSize = appFileSize;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getAppVersionNo() {
            return appVersionNo;
        }

        public void setAppVersionNo(String appVersionNo) {
            this.appVersionNo = appVersionNo;
        }

        public String getAppBuildVersion() {
            return appBuildVersion;
        }

        public void setAppBuildVersion(String appBuildVersion) {
            this.appBuildVersion = appBuildVersion;
        }

        public String getAppIdentifier() {
            return appIdentifier;
        }

        public void setAppIdentifier(String appIdentifier) {
            this.appIdentifier = appIdentifier;
        }

        public String getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(String appIcon) {
            this.appIcon = appIcon;
        }

        public String getAppDescription() {
            return appDescription;
        }

        public void setAppDescription(String appDescription) {
            this.appDescription = appDescription;
        }

        public String getAppUpdateDescription() {
            return appUpdateDescription;
        }

        public void setAppUpdateDescription(String appUpdateDescription) {
            this.appUpdateDescription = appUpdateDescription;
        }

        public String getAppScreenshots() {
            return appScreenshots;
        }

        public void setAppScreenshots(String appScreenshots) {
            this.appScreenshots = appScreenshots;
        }

        public String getAppShortcutUrl() {
            return appShortcutUrl;
        }

        public void setAppShortcutUrl(String appShortcutUrl) {
            this.appShortcutUrl = appShortcutUrl;
        }

        public String getAppCreated() {
            return appCreated;
        }

        public void setAppCreated(String appCreated) {
            this.appCreated = appCreated;
        }

        public String getAppUpdated() {
            return appUpdated;
        }

        public void setAppUpdated(String appUpdated) {
            this.appUpdated = appUpdated;
        }

        public String getAppQRCodeURL() {
            return appQRCodeURL;
        }

        public void setAppQRCodeURL(String appQRCodeURL) {
            this.appQRCodeURL = appQRCodeURL;
        }

        public String getAppFollowed() {
            return appFollowed;
        }

        public void setAppFollowed(String appFollowed) {
            this.appFollowed = appFollowed;
        }

        public String getOtherAppsCount() {
            return otherAppsCount;
        }

        public void setOtherAppsCount(String otherAppsCount) {
            this.otherAppsCount = otherAppsCount;
        }

        public String getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(String commentsCount) {
            this.commentsCount = commentsCount;
        }

        public List<OtherappsBean> getOtherapps() {
            return otherapps;
        }

        public void setOtherapps(List<OtherappsBean> otherapps) {
            this.otherapps = otherapps;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public static class OtherappsBean {
            /**
             * appKey : c777f4ffbc6a50d9c5b354c043bfa028
             * userKey : 39b74627fbddf33a208e48610fdeee4b
             * appName : 大闹天宫Pro
             * appVersion : 1.2.1
             * appBuildVersion : 4
             * appIdentifier : com.wenba.pro.havocinheaven
             * appCreated : 2017-09-06
             * appUpdateDescription :
             */

            private String appKey;
            private String userKey;
            private String appName;
            private String appVersion;
            private String appBuildVersion;
            private String appIdentifier;
            private String appCreated;
            private String appUpdateDescription;

            public String getAppKey() {
                return appKey;
            }

            public void setAppKey(String appKey) {
                this.appKey = appKey;
            }

            public String getUserKey() {
                return userKey;
            }

            public void setUserKey(String userKey) {
                this.userKey = userKey;
            }

            public String getAppName() {
                return appName;
            }

            public void setAppName(String appName) {
                this.appName = appName;
            }

            public String getAppVersion() {
                return appVersion;
            }

            public void setAppVersion(String appVersion) {
                this.appVersion = appVersion;
            }

            public String getAppBuildVersion() {
                return appBuildVersion;
            }

            public void setAppBuildVersion(String appBuildVersion) {
                this.appBuildVersion = appBuildVersion;
            }

            public String getAppIdentifier() {
                return appIdentifier;
            }

            public void setAppIdentifier(String appIdentifier) {
                this.appIdentifier = appIdentifier;
            }

            public String getAppCreated() {
                return appCreated;
            }

            public void setAppCreated(String appCreated) {
                this.appCreated = appCreated;
            }

            public String getAppUpdateDescription() {
                return appUpdateDescription;
            }

            public void setAppUpdateDescription(String appUpdateDescription) {
                this.appUpdateDescription = appUpdateDescription;
            }

            @Override
            public String toString() {
                return "OtherappsBean{" +
                        "appKey='" + appKey + '\'' +
                        ", userKey='" + userKey + '\'' +
                        ", appName='" + appName + '\'' +
                        ", appVersion='" + appVersion + '\'' +
                        ", appBuildVersion='" + appBuildVersion + '\'' +
                        ", appIdentifier='" + appIdentifier + '\'' +
                        ", appCreated='" + appCreated + '\'' +
                        ", appUpdateDescription='" + appUpdateDescription + '\'' +
                        '}';
            }
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "appKey='" + appKey + '\'' +
                    ", userKey='" + userKey + '\'' +
                    ", appType='" + appType + '\'' +
                    ", appIsLastest='" + appIsLastest + '\'' +
                    ", appFileSize='" + appFileSize + '\'' +
                    ", appName='" + appName + '\'' +
                    ", appVersion='" + appVersion + '\'' +
                    ", appVersionNo='" + appVersionNo + '\'' +
                    ", appBuildVersion='" + appBuildVersion + '\'' +
                    ", appIdentifier='" + appIdentifier + '\'' +
                    ", appIcon='" + appIcon + '\'' +
                    ", appDescription='" + appDescription + '\'' +
                    ", appUpdateDescription='" + appUpdateDescription + '\'' +
                    ", appScreenshots='" + appScreenshots + '\'' +
                    ", appShortcutUrl='" + appShortcutUrl + '\'' +
                    ", appCreated='" + appCreated + '\'' +
                    ", appUpdated='" + appUpdated + '\'' +
                    ", appQRCodeURL='" + appQRCodeURL + '\'' +
                    ", appFollowed='" + appFollowed + '\'' +
                    ", otherAppsCount='" + otherAppsCount + '\'' +
                    ", commentsCount='" + commentsCount + '\'' +
                    ", otherapps=" + otherapps +
                    ", comments=" + comments +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppInfoDetail{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}