<#if syn=='0'>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>春意小说精选</title>
    <link rel="stylesheet" href="/css/reset_5.css">
    <script src="/js/baidu-statis.js"></script>
</head>
<body>
<div class="indexHeader">
    <img src="/images/icon/logo.png" alt="" class="logoImg" onclick="window.location.reload()"/>
    <div class="logoName">春意小说</div>
    <div class="indexHeaderStackBox" onclick="goShelf()">
        <img src="/images/icon/bookshelfIcon.png" alt="" class="indexHeaderStack" />
        <span class="indexHeaderTxt">书架</span>
    </div>
    <div class="indexUser">
        <img src="<#if user??>${user.logo}<#else>/images/icon/userIcon1.png</#if>" onerror="javascript:this.src='/images/other/userImg.jpg';" alt="" class="indexUserImg" onclick="goUcenter()" />
    </div>
</div>
<div class="indexSreach">
    <img src="/images/icon/sreachIcon.png" alt="" class="indexSreachImg" />
    <input type="text" value="" readOnly='readOnly' id="searchInput" onclick="goSearch()" placeholder="<#if searchBook??>${searchBook}</#if>" class="indexSreachInput">
</div>
<div class="indexNav">
    <div class="indexNavBox">
        <div onclick="rankList(9)">
            <img src="/images/icon/indexIcon1.png" alt="" class="indexFree">
            <div>免费</div>
        </div>
        <div onclick="goCategory()">
            <img src="/images/icon/indexIcon2.png" alt="" class="indexFree">
            <div>分类</div>
        </div>
        <div onclick="rankList(7)">
            <img src="/images/icon/indexIcon3.png" alt="" class="indexFree">
            <div>完本</div>
        </div>
        <div onclick="rankList(8)">
            <img src="/images/icon/indexIcon4.png" alt="" class="indexFree">
            <div>新书</div>
        </div>
    </div>
    <div class="indexNavBox">
        <div onclick="rankList(6)">
            <img src="/images/icon/indexIcon5.png" alt="" class="indexFree">
            <div>热销</div>
        </div>
        <div onclick="rankList(2)">
            <img src="/images/icon/indexIcon6.png" alt="" class="indexFree">
            <div>男生</div>
        </div>
        <div onclick="rankList(3)">
            <img src="/images/icon/indexIcon7.png" alt="" class="indexFree">
            <div>女生</div>
        </div>
        <div onclick="rankList(4)">
            <img src="/images/icon/indexIcon8.png" alt="" class="indexFree">
            <div>二次元</div>
        </div>
    </div>
</div>
<div class="downBannerBox indexdownBanner" style="display:none" id="downBanner" onclick="downloadApp('${channel}')">
    <input type="button" value="领取福利" class="btn">
    <img src="/images/other/banner.png" alt="" class="downBanner">
</div>
<div class="pd1Box pageLoad">
</#if>
    <#if pageFinder.data??>
        <#list pageFinder.data as driveBook>
            <section class="bookListBox" onclick="bookInfo(${driveBook.book.bookId?c})">
                <img class="bookListImg" data-echo="${driveBook.book.coverUrl}" src="/images/other/default.jpg" onerror="javascript:this.src='/images/other/default.jpg'">
                <div class="bookList">
                    <div class="bookName">${driveBook.book.title}</div>
                    <div class="bookInfo">
                        <#if driveBook.book.intro??>
                            ${driveBook.book.intro?replace("　","")?replace("　","")}
                        </#if>
                    </div>
                    <div class="authorBox">
                        <div class="authorNmae">${driveBook.book.authorPenname}</div>
                        <div class="bookGenre">
                            <div class="bookGenrePublic">${driveBook.book.categorySecName}</div>
                            <div class="bookGenrePublic bookGenrePublicStyle">${driveBook.book.categoryThrName}</div>
                        </div>
                    </div>
                </div>
            </section>
        </#list>
    </#if>
<#if syn=='0'>
</div>
<div class="bookLoad mt0" id="autopbn" curpage="${pageFinder.pageNo+1}" totalpage="${pageFinder.pageCount}" rel="/portal/portalIndex.go?page=${pageFinder.pageNo+1}&syn=1&type=${type}" style="display:none;"></div>
<!--<div class="footerBgBox"></div>
<div class="downBannerBox" onclick="downloadApp()">
    <input type="button" value="领取福利" class="btn">
    <img src="/images/other/banner.png" alt="" class="downBanner">
</div>
<small class="inedexCopyright">
    Copyright&copy;版权所有归春意小说所有
</small>-->
<script type="text/javascript" src="/js/base.js"></script>
<script type="text/javascript" src="/js/autopage.js"></script>
<script type="text/javascript" src="/js/echo.min.js"></script>
<#include "../common/common.ftl"/>
<script>
    if (navigator.userAgent.match(/Android/i)) {
        $("#downBanner").show()
    }

    // 加载页面时候的样式
    $(function () {
        $('.downBannerBox').css('bottom', $('.inedexCopyright').outerHeight(false));
        $('.footerBgBox').height($('.downBannerBox').outerHeight(false) + $('.inedexCopyright').outerHeight(false));
    });

    function bookInfo(bookId) {
        var url = "/book/bookDetail.go?bookId="+bookId;
        window.location.href=url;
    }

    function rankList(type){
        var url = "/portal/rankList.go?type="+type;
        window.location.href=url;
    }

    function goCategory(){
        var url = "/portal/categoryIndex.go";
        window.location.href=url;
    }

    function goShelf(){
        var url = "/user/queryUserShelf.go?type=1";
        window.location.href=url;
    }

    function downloadApp(channel){
        var url = "http://quanshunjt.com/doc/app-channel100000-release.apk";
        if(channel == '300001'){
            url = "http://quanshunjt.com/doc/app-channel100015-release.apk";
        }else if(channel == '300002'){
            url = "http://quanshunjt.com/doc/app-channel100016-release.apk";
        }else if(channel == '300003'){
            url = "http://quanshunjt.com/doc/app-channel100017-release.apk";
        }else if(channel == '300004'){
            url = "http://quanshunjt.com/doc/app-channel100018-release.apk";
        }else if(channel == '300005'){
            url = "http://quanshunjt.com/doc/app-channel100019-release.apk";
        }else if(channel == '300006'){
            url = "http://quanshunjt.com/doc/app-channel100020-release.apk";
        }else if(channel == '300007'){
            url = "http://quanshunjt.com/doc/app-channel100021-release.apk";
        }else if(channel == '300008'){
            url = "http://quanshunjt.com/doc/app-channel100022-release.apk";
        }else if(channel == '300009'){
            url = "http://quanshunjt.com/doc/app-channel100023-release.apk";
        }else if(channel == '300010'){
            url = "http://quanshunjt.com/doc/app-channel100024-release.apk";
        }else if(channel == '300011'){
            url = "http://quanshunjt.com/doc/app-channel100025-release.apk";
        }
        window.open(url);
    }

    function goSearch(el){
        var text = $("#searchInput").attr("placeholder");
        var url = "/search/searchIndex.go?text=" + encodeURI(encodeURI(text));
        window.location.href=url;
    }

    function goUcenter(){
        var url = "/user/userCenter.go";
        window.location.href=url;
    }
</script>
</#if>
<script>
    Echo.init({
        offset: 0,
        throttle: 0
    });
</script>
<#if syn=='0'>
</body>
</html>
</#if>