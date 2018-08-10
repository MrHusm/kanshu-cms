package com.yxsd.kanshu.log;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"Convert2Diamond", "deprecation"})
public class UrlManager implements Serializable {
    /*
     * 非常重要，不要轻易修改
     */
    public final static String IMG = "img";// 付费书籍列表
    //v1.6
    private static final String COMPETITIVEHOST = "competitivehost_url";
    private static final String RANKINGSHOST = "rankingshost_url";
    private static final String SEARCHHOST = "searchhost_url";
    private static final String BIRTHDAY = "birthday_url";
    private static final String PICUPDATE = "picupdate_url";
    private static final String BOOKDETAIL = "bookdetail_url";

    // for version2
    public final static String CHAPTER = "chapter";// 获取章节内容
    public final static String VERSIONINFO = "versioninfo";// 版本信息
    private final static String LIVE_INDEX = "live_index"; // 直播首页
    private final static String LIVE_ATTENTION =  "live_attention";        //直播关注

    private static final String SHAREIMGURL = "share_img_url";
    private static final String SHARECONTENTURL = "share_content_url";
    private static final String SECRETPOSTURL = "maidian_post_url";
    private static final String ADSPOSTURL = "ads_post_url";

    //-----------------Version 2.2.1 -------------------------------
    private final static String GET_CHAPTER_CONTENT = "get_chapter_content";
    //-----------------version 2.6.0-------------------------
    private static final String MYINTEGRAL = "myintegral";
    private static final String RECOMMEND_URL = "recommend_url";

    //-------------------v3.2.0----------------
    private static final String MYCENTER = "my_center";
    private static final String BINDTHIRDUSER = "bind_third_user";
    //-------------------v3.3.0----------------
    private static final String EXTENSION = "extension";

    private static final String DOMAIN_APP = "http://cx.ikanshu.cn";
    private static final String DOMAIN_LIVE = "http://app.live.cread.com";
    private static final String DOMAIN_AD = "http://ad.cread.com";

    private static boolean isInit = false;
    private static Map<String, String> mUrlMap = new HashMap<String, String>();

    //private static GlobalApp app = GlobalApp.getInstance();


    private static String mImgsUrl = "http://imgs.ikanshu.cn";
    private static String mMainUrl = DOMAIN_APP;
    private static String mSecretUrl = "http://jiami.ikanshu.cn";
    private static String mZlogUrl = "http://zlog.ikanshu.cn";
    private static String mLiveUrl = DOMAIN_LIVE;
    private static String mAdUrl = DOMAIN_AD;

    //-------------------v2.4.1----------------
    private final static String VIP_STATUS = "vip_status";

    //-------------------v3.1.0----------------
    private static String mAYangAPIUrl = "http://api.ssp.securecloud.com.cn/api/def";
    protected static String mZwyhRootUrl = "http://zwyh.ikanshu.cn";  // 中文书城接口、用于获取咪咕扩展数据
    protected static String mMiGuRootUr = "http://wap.cmread.com"; // 咪咕接口


    public static String getVersionName() {
        return "420";
    }

    public static void init() {

        String target = "release";//GlobalApp.getInstance().getTarget();
        if ("release".equals(target)) {
            mMainUrl = DOMAIN_APP;
            mLiveUrl = DOMAIN_LIVE;
            mAdUrl = DOMAIN_AD;
        } else if ("beta".equals(target)) {
            mMainUrl = "http://cxtest1.ikanshu.cn";
            mLiveUrl = "http://60.29.240.225:8080";
            mAdUrl = DOMAIN_AD;
        } else {
            mMainUrl = "http://cxtest.ikanshu.cn:8899";
            mLiveUrl = "http://124.205.182.42:8180";
            mAdUrl = "http://192.168.1.242:8088";
        }
        handleBaseUrlJsonString();
        isInit = true;
    }

    private static void handleBaseUrlJsonString() {
        addUrl(IMG, "http://cdn.ikanshu.cn/book_covers");
        addUrl(CHAPTER, mMainUrl + "/cx/itf/chapterRead");
        addUrl(VERSIONINFO, mMainUrl + "/cx/itf/checkVersion");

        //V1.5  ygzhang

        addUrl(COMPETITIVEHOST, mMainUrl + "/cx/index");
        addUrl(RANKINGSHOST, mMainUrl + "/cx/phindex");
        addUrl(SEARCHHOST, mMainUrl + "/cx/searchindex");
        addUrl(BIRTHDAY, mMainUrl + "/cx/itf/updateBirthDay");
        addUrl(PICUPDATE, mMainUrl + "/cx/itf/uploadIcon");
        addUrl(BOOKDETAIL, mMainUrl + "/cx/bookdetail");

        //v2.1.0 zx
        addUrl(SHAREIMGURL, mMainUrl + "/static/images/icon_share.png");
        addUrl(SHARECONTENTURL, mMainUrl + "/cx/shareGO");
        addUrl(SECRETPOSTURL, mSecretUrl + "/proto/parseLog");
        addUrl(ADSPOSTURL, mMainUrl + "/cx/itf/clickAdLog");

        //V2.2.1 ygzhang
        addUrl(GET_CHAPTER_CONTENT, mMainUrl + "/cx/itf/loadingChapter");

        //V2.6.0 zxing
        addUrl(MYINTEGRAL, mMainUrl + "/cx/userscore/taskIntegral");
        addUrl(RECOMMEND_URL, mMainUrl + "/cx/user/discover");

        //V2.4.1 zxing
        addUrl(VIP_STATUS, mMainUrl + "/cx/itf/userbyinfo");

        //V3.2.0 zxing
        addUrl(MYCENTER, mMainUrl + "/cx/user/userCenter");
        addUrl(BINDTHIRDUSER, mMainUrl + "/cx/user/bindThirdAccount");
        //V3.3.0 zp
        addUrl(EXTENSION, mZwyhRootUrl + "/interface!getExtendInfo.xhtml");

        // live
        addUrl(LIVE_INDEX, mLiveUrl + "/external/tab/index");
        //live-attention
        addUrl(LIVE_ATTENTION, mLiveUrl + "/external/app/user/follow/list");
    }

    public static String getMainUrl() {
        return mMainUrl;
    }

    public static String getMiGuUrl() {
        return mMiGuRootUr;
    }

    public static void setMainUrl(String mMainUrl) {
        UrlManager.mMainUrl = mMainUrl;
        UrlManager.mUrlMap.clear();
        handleBaseUrlJsonString();
    }

    public static String getLiveHostUrl() {
        return mLiveUrl;
    }

    public static String getImgsUrl() {
        return mImgsUrl;
    }

    public static void setImgsUrl(String mImgsUrl) {
        UrlManager.mImgsUrl = mImgsUrl;
    }

    public static String getSecretUrl() {
        return mSecretUrl;
    }

    public static void setSecretUrl(String mSecretUrl) {
        UrlManager.mSecretUrl = mSecretUrl;
        UrlManager.mUrlMap.clear();
        handleBaseUrlJsonString();
    }

    public static String getZlogUrl() {
        return mZlogUrl;
    }

    public static void setZlogUrl(String mZlogUrl) {
        UrlManager.mZlogUrl = mZlogUrl;
    }

    private static void addUrl(String key, String value) {
        mUrlMap.put(key, value);
    }

    public static String getUrl(String key) {
        if (!isInit) {
            handleBaseUrlJsonString();
            if (mUrlMap.size() > 0) {
                isInit = true;
            }
        }
        return mUrlMap.get(key);
    }

    /*
     * -----------------------for version2-------------------------
     */
    public static String getImg(String coverImgId) {
        return getUrl(IMG) + "/" + coverImgId;
    }

    public static String getBookCoverImg(String coverUrl, String bookId) {// helpful
        // functions
        if (coverUrl == null || coverUrl.length() == 0) {
            return (getImg(bookId + ".jpg"));
        } else {
            return (getImg(coverUrl));
        }
    }


    public static String getVersionInfoUrl() {
        return getUrlForMoreParams(getUrl(VERSIONINFO));
    }


    //--------------------------------V1.6.0-------------------------------ygzhang

    /**
     * 获取排行版数据
     *
     * @return Url
     */
    public static String getRanksHostUrl() {
        return getUrlForMoreParams(getUrl(RANKINGSHOST));
    }

    /**
     * 获取我的积分页数据
     */
    public static String getMyIntegral() {
        return getUrlForMoreParams(getUrl(MYINTEGRAL));
    }

    public static String getBindThirduserUrl() {
        return getUrlForMoreParams(getUrl(BINDTHIRDUSER) + "?uid=" + 1000);
    }

    public static String getMyCenterUrl() {
        return getUrlForMoreParams(getUrl(MYCENTER) + "?uid=" + 1000);
    }

    public static String getShelfAdImageUrl(String url) {
        return mImgsUrl + "/cx/" + url;
    }

    public static String getBirthday(String uid, String birth) {
        return getUrlForMoreParams(getUrl(BIRTHDAY) + "?uid=" + uid + "&birthDay=" + birth);
    }

    public static String getPicUpdateUrl(String uid) {
        return getUrlForMoreParams(getUrl(PICUPDATE) + "?uid=" + uid);
    }

    public static String getCommonUrl(String url) {
        return getUrlForMoreParams(mMainUrl + "/" + url);
    }

    /**
     * 阅读结束推荐页url
     */
    public static String getRecommend(String uid, String bookid, String bookName) {
        return getUrlForMoreParams(mMainUrl + "/cx/bookend?uid=" + uid + "&bookid=" + bookid + "&bookname=" + bookName);
    }

    /**
     * 获取详情页地址
     *
     * @return Url
     */
    public static String getDetailUrl(String bookid) {

        return getUrlForMoreParams(getUrl(BOOKDETAIL) + "?bookid=" + bookid);
    }


    public static String getReportErrorUrl() {
        return getUrlForMoreParams("http://zlog.ikanshu.cn/err.html");
    }

    public static String getShareImgUrl() {
        return getUrl(SHAREIMGURL);
    }

    public static String getShareContentUrl() {
        return getUrl(SHARECONTENTURL);
    }

    public static String getWXShareContentUrl() {
        return "http://wechat.iwanvi.cn/cx/share-clien-wx.html";
    }

    public static String getClientDataUrl() {
        return  "http://zwyh.ikanshu.cn/validate!validateData.xhtml";
    }

    public static String getReportDatasUrl() {
        return getUrl(SECRETPOSTURL);
    }

    public static String getReportAdsUrl() {
        return getUrl(ADSPOSTURL);
    }

    /**
     * @param dstUrl 目标url
     * @return 添加公共参数
     */
    public static String getUrlForMoreParams(String dstUrl) {
        StringBuffer url = new StringBuffer(dstUrl);
        try {
            if (dstUrl.contains("?")) {
                url.append("&");
            } else {
                url.append("?");
            }
            url.append("cnid=").append("1062");
            url.append("&umeng=").append("test");
            url.append("&version=").append("4.1.0");
            url.append("&vercode=").append("68");
            url.append("&imei=").append("868930026739028");
            url.append("&imsi=").append("");
            url.append("&uid=").append("19356018");
            url.append("&packname=").append("com.mianfeia.book");
            url.append("&oscode=").append("24");
            url.append("&model=").append("Ml+5");
            url.append("&other=a");
            url.append("&vcode=").append("68");
            url.append("&channelId=").append("1062");
            url.append("&mac=").append("5cl459833364eel8297e9d5e3006d09");
            url.append("&platform=").append("android")
                    .append("&appname=").append("cxb");
            url.append("&brand=").append("Xiaomi");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url.toString();
    }


    /**
     * 获取设备信息
     *
     * @return
     */
    public static DeviceInfo getDeviceInfo() {
        DeviceInfo info = new DeviceInfo();
        try {
            info.setUid("1000");
            info.setCnId("3000");
            info.setAppname("3000");
            info.setBrand("3000");
            info.setPlatform("android");
            String macAddress = "000000000000";
            if (StringUtils.isEmpty(macAddress)) {
                macAddress = "000000000000";
            }
            info.setMac(URLEncoder.encode(macAddress, "UTF-8"));
            info.setCnName("3000");
            info.setVerCode("3000");
            info.setOscode("3000");
            info.setVersion("3000");
            info.setModel("3000");
            info.setPkgName("3000");
            info.setImei("3000");
            info.setImsi("3000");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return info;
    }


    //--------------------------------V2.2.0-------------------------------ygzhang
    public static String getPushUrl(String url) {
        return getUrlForMoreParams(url);
    }

    public static String getPushCid(String cid) {
        return getUrlForMoreParams(mMainUrl + "/cx/itf/gtInfo?cid=" + cid);
    }

    /**
     * @desc 获取章节内容新接口
     * @author ygzhang
     * @time 2016/12/20
     * @version V3.1.0
     */
    public static String getChapterUrl() {
        return getUrl(CHAPTER);
    }

    /**
     * @desc 以文件流形式获取章节内容
     * @author ygzhang
     * @time 2016/12/15
     * @version V310
     */
    public static String getChapterContentUrl() {
        return getUrl(GET_CHAPTER_CONTENT);
    }

    /**
     * @desc 积分商城url
     * @author zxing
     * @time 2016-11-02
     * @version V2.7.1
     */
    public static String getIntegralShopUrl() {
        return "/cx/intShop/duibashop";
    }


    /**
     * @desc 获取今日推荐url地址
     * @author ygzhang
     * @time 16/8/31
     * @version V260
     */
    public static String getRecommendUrl() {
        return getUrlForMoreParams(getUrl(RECOMMEND_URL));
    }

    //------------------------3.0 Add---------------------------/
    public static String getLivePluginUrl(String version, String flag) {
        return "/external/android/check.json?version=" + version+"&app=" + flag;
    }

    /**
     * 登录页面
     *
     * @return String
     */
    public static String getLoginHtml() {
        return mMainUrl + "/pages/login.jsp";
    }


    /**
     * @desc 积分规则url
     * @author zxing
     * @time 2016-11-02
     * @version V
     */
    public static String getIntegralRulesUrl() {
        return "cx/intShop/toRules";
    }

    /**
     * 阿洋API广告接口
     *
     * @author zxing
     * @time 2016-11-28
     * @version V2.7.3
     */
    public static String getAYangAPIUrl() {
        return mAYangAPIUrl;
    }


    /**
     * 获取扩展信息接口
     *
     * @param id 扩展标识
     * @return String
     */
    public static String getExtensionUrl(String id) {
        return getUrlForMoreParams(getUrl(EXTENSION) + "?id=" + id);
    }

    /**
     * 切换创新版直播访问url，仅用于测试
     *
     * @param url
     */
    public static void setLiveUrl(String url) {
        mLiveUrl = url;
        mUrlMap.clear();
        handleBaseUrlJsonString();
    }

    /**
     * 返回当前创新版直播访问url，仅用于测试
     *
     * @return
     */
    public static String getLiveUrl() {
        return mLiveUrl;
    }


}
