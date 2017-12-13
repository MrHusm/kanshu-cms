<#if syn=='0'>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>搜索书籍</title>
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

    <div class="pd1Box pageLoad">
</#if>
	<#if searchBooks??>
		<#list searchBooks as book>
            <section class="bookListBox" onclick="bookInfo(${book.bookId?c})">
                <img class="bookListImg" data-echo="${book.coverUrl}" src="/images/other/default.jpg" onerror="javascript:this.src='/images/other/default.jpg';">
                <div class="bookList">
                    <div class="bookName">${book.title}</div>
                    <div class="bookInfo">
                        <#if book.intro??>
                            ${book.intro?replace("　","")?replace("　","")}
						</#if>
                    </div>
                    <div class="authorBox">
                        <div class="authorNmae">${book.authorPenname}</div>
                        <div class="bookGenre">
                            <div class="bookGenrePublic">${book.categorySecName}</div>
                            <div class="bookGenrePublic bookGenrePublicStyle">${book.categoryThrName}</div>
                        </div>
                    </div>
                </div>
            </section>
		</#list>
    <#else>
        <script>
            $("#autopbn").remove();
            window.onscroll = null;
        </script>
	</#if>
<#if syn=='0'>
    </div>
    <div class="bookLoad" id="autopbn" curpage="${page+1}" totalpage="100" rel="/search/search.go?page=${page+1}&searchText=${searchText}&syn=1" style="display:none;"></div>
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
            var url = "/search/search.go?searchText="+searchText;
            window.location.href=url;
        }
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