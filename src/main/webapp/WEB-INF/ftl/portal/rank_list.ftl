<#if syn=='0'>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" class="newBg" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>榜单</title>
    <link rel="stylesheet" href="/css/reset_5.css">
</head>
<body>
<#include "../common/head.ftl"/>
<div class="bookHeader">
    <img src="/images/icon/backing.png" alt="" onclick="window.history.go(-1)" class="headerImg left">
    <div class="headerMidden">
        <#if type='2'>男生
        <#elseif type='3'>女生
        <#elseif type='4'>二次元
        <#elseif type='6'>热销
        <#elseif type='7'>完本
        <#elseif type='8'>新书
        <#elseif type='9'>免费
        </#if>
    </div>
    <img src="/images/icon/menu.png" alt="" class="headerImg right" onclick="twoLevelNavToggle()">
</div>
<#if type='9'><div class="freeTxt">百本图书限时免费看！每日持续更新</div></#if>
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
<div class="bookLoad mt0" id="autopbn" curpage="${pageFinder.pageNo+1}" totalpage="${pageFinder.pageCount}" rel="/portal/rankList.go?page=${pageFinder.pageNo+1}&syn=1&type=${type}" style="display:none;"></div>
<script type="text/javascript" src="/js/base.js"></script>
<script type="text/javascript" src="/js/autopage.js"></script>
<script type="text/javascript" src="/js/echo.min.js"></script>
<#include "../common/common.ftl"/>
<script>
    function bookInfo(bookId) {
        var url = "/book/bookDetail.go?bookId="+bookId;
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