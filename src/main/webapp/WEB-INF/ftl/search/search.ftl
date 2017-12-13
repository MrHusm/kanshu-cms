<#if syn=='0'>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" class="newBg" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>搜索</title>
    <link rel="stylesheet" href="/css/reset_5.css">
    <link rel="stylesheet" href="/css/postbirdAlertBox.css">
    <script type="text/javascript" src="/js/postbirdAlertBox.js" />
</head>
<body>
<#include "../common/head.ftl"/>
<div class="bookHeader newbookHeader newClear">
    <div>
        <img src="/images/icon/backing.png" alt="" height="16" class="headerImg left mt0"  onclick="window.history.go(-1)">
    </div>
    <div class="newSreachTop">
        <div class="searchInput">
            <input type="text" id="searchText" value="<#if searchText??>${searchText}</#if>" placeholder="请输入书名、作者、关键词" style="width:100%;height:100%;background:none;outline:none;">
        </div>
        <div>
            <input type="button" onclick="search()" value="搜索" class="searchBtn" style="height: 32px;">
        </div>
    </div>
    <div>
        <img src="/images/icon/menu.png" alt="" height="16" class="headerImg right mt0" onclick="twoLevelNavToggle()">
    </div>
</div>

<#if type?? && type='1'>
    <div class="notResult">
        <img src="/images/icon/notIcon.png" alt=""  class="notResultIcon"/>
        <span class="notResultTxt">很遗憾，没有相关的搜索结果</span>
    </div>
<#else>
    <div class="tag newTag newFreeDivision">
        <div class="tagPub"><span class="tagCont tagPubStyle1" onclick="tagSearch('玄幻')">玄幻</span></div>
        <div class="tagPub"><span class="tagCont tagPubStyle2" onclick="tagSearch('都市')">都市</span></div>
        <div class="tagPub"><span class="tagCont tagPubStyle3" onclick="tagSearch('现代言情')">现代言情</span></div>
        <div class="tagPub"><span class="tagCont tagPubStyle4" onclick="tagSearch('古代言情')">古代言情</span></div>
        <div class="tagPub"><span class="tagCont tagPubStyle1" onclick="bookInfo(31886)">末日乐园</span></div>
        <div class="tagPub"><span class="tagCont tagPubStyle2" onclick="bookInfo(14408)">武神血脉</span></div>
    </div>
</#if>

<div class="hr"></div>
<div class="pd1Box pageLoad">
    <div class="h6"><i class="h6Icon"></i>大家都在搜</div>
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
<div class="bookLoad mt0" id="autopbn" curpage="${pageFinder.pageNo+1}" totalpage="${pageFinder.pageCount}" rel="/search/searchIndex.go?page=${pageFinder.pageNo+1}&syn=1" style="display:none;"></div>
<script type="text/javascript" src="/js/base.js"></script>
<script type="text/javascript" src="/js/autopage.js"></script>
<script type="text/javascript" src="/js/echo.min.js"></script>
<#include "../common/common.ftl"/>
<script>
    function bookInfo(bookId) {
        var url = "/book/bookDetail.go?bookId="+bookId;
        window.location.href=url;
    }

    function search(){
        var searchText = trim($("#searchText").val());
        if(searchText == null || searchText == ''){
            PostbirdAlertBox.alert({'content': '请输入搜索词'});
        }else{
            var url = "/search/search.go?searchText="+ searchText;
            window.location.href=url;
        }
    }

    function tagSearch(searchText){
        var url = "/search/search.go?searchText="+ searchText;
        window.location.href=url;
    }

    function trim(x) {
        if(x == null){
            return null;
        }else{
            return x.replace(/^\s+|\s+$/gm,'');
        }
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