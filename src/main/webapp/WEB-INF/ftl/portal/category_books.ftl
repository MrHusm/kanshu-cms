<#if syn=='0'>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <title>${category.name}</title>
    <link rel="stylesheet" href="/css/reset_5.css">
    <script type="text/javascript">
        var _categoryId = ${categoryId};
        var _childCategoryId = <#if childCategoryId??>${childCategoryId}<#else>null</#if>;
        var _isFull = <#if isFull??>${isFull}<#else>null</#if>;

        function userClick(obj,categoryId,childCategoryId,id){
            _categoryId = categoryId;
            _childCategoryId = childCategoryId;
            if ($(obj).is('.classifyBoxTit')) {
                $('.classifyBoxTit1').removeClass('classifyBox2Active');
                $('.classifyBoxTit1').next().find('li').removeClass('classifyBox2Active');
                $('.classifyBoxTit1').addClass('classifyBox2Active');
            }else {
                var val = $(obj).data('id');
                $('.classifyBoxUl1').find('li').each(function (i,el) {
                    if ($(el).data('id') == val) {
                        $(this).parent().prev().removeClass('classifyBox2Active');
                        $(this).siblings('li').removeClass('classifyBox2Active');
                        $(this).addClass('classifyBox2Active');
                    }
                })
            }
            $(id).text();
            $(id).text($(obj).text());

            var url = "/portal/categoryBooks.go?categoryId="+_categoryId+"&childCategoryId="+(_childCategoryId==null?'':_childCategoryId)+"&isFull="+(_isFull==null?"":_isFull);
            window.location.href=url;
        }
        function userClick2(obj,isFull,id){
            _isFull = isFull;
            if ($(obj).is('.classifyBoxTit')) {
                $('.classifyBoxTit2').removeClass('classifyBox2Active');
                $('.classifyBoxTit2').next().find('li').removeClass('classifyBox2Active');
                $('.classifyBoxTit2').addClass('classifyBox2Active');
            }else {
                var val = $(obj).data('id');
                $('.classifyBoxUl2').find('li').each(function (i,el) {
                    if ($(el).data('id') == val) {
                        $(this).parent().prev().removeClass('classifyBox2Active');
                        $(this).siblings('li').removeClass('classifyBox2Active');
                        $(this).addClass('classifyBox2Active');
                    }
                })
            }
            $(id).text();
            $(id).text($(obj).text());

            var url = "/portal/categoryBooks.go?categoryId="+_categoryId+"&childCategoryId="+(_childCategoryId==null?'':_childCategoryId)+"&isFull="+(_isFull==null?"":_isFull);
            window.location.href=url;
        }

        function userClick3() {
            $('.newClassifyBox2').show();
            $('.newClassifyBox1').css('top','-41px');
        }

        function bookInfo(bookId) {
            var url = "/book/bookDetail.go?bookId="+bookId;
            window.location.href=url;
        }
    </script>
</head>
<body class="newBg">
<#include "../common/head.ftl"/>
<div class="bookHeader">
    <img src="/images/icon/backing.png" alt="" class="headerImg left" onclick="window.history.go(-1)">
    <div class="headerMidden">${category.name}</div>
    <img src="/images/icon/menu.png" alt="" class="headerImg right" onclick="twoLevelNavToggle()">
</div>

<div id="classifyBoxTwo" class="classifyBox newClassifyBox">
    <div class="classifyBox1 newClassifyBox1" onclick="userClick3()">
        <span id="selectType">${category.name}</span>、<span id="selectType2">不限</span><img class="classifyBoxImg" src="/images/icon/downIcon.png" alt="" />
    </div>
    <div class="classifyBox2 newClassifyBox2 none">
        <div class="newClear">
            <div class="classifyBoxTit classifyBoxTit1 <#if !childCategoryId?? || childCategoryId==''>classifyBox2Active</#if>" data-id="tit1-1" onclick="userClick(this,${category.categoryId?c},'',selectType)">${category.name}</div>
            <ul class="classifyBoxUl classifyBoxUl1 newClear">
                <#if childCategorys??>
                    <#list childCategorys as childCategory>
                        <li <#if  childCategoryId?? && childCategoryId == '${childCategory.categoryId?c}'>class="classifyBox2Active"</#if> data-id="tit1-${childCategory_index+2}" onclick="userClick(this,${category.categoryId?c},${childCategory.categoryId?c},selectType)">${childCategory.name}</li>
                    </#list>
                </#if>
            </ul>
        </div>
        <div class="newClear">
            <div class="classifyBoxTit classifyBoxTit2 <#if !isFull?? || isFull==''>classifyBox2Active</#if>" data-id="tit2-1" onclick="userClick2(this,'',selectType2)">不限</div>
            <ul class="classifyBoxUl classifyBoxUl2 newClear">
                <li data-id="tit2-2" <#if isFull?? && isFull=='1'>class="classifyBox2Active"</#if> onclick="userClick2(this,1,selectType2)">完结</li>
                <li data-id="tit2-3" <#if isFull?? && isFull=='0'>class="classifyBox2Active"</#if> onclick="userClick2(this,0,selectType2)">连载</li>
            </ul>
        </div>
    </div>
</div>

<div id="classifyBoxOne" class="classifyBox">
    <div class="classifyBox2">
        <div class="newClear">
            <div class="classifyBoxTit classifyBoxTit1 <#if !childCategoryId?? || childCategoryId==''>classifyBox2Active</#if>" data-id="tit1-1" onclick="userClick(this,${category.categoryId?c},'',selectType)">${category.name}</div>
            <ul class="classifyBoxUl classifyBoxUl1 newClear">
                <#if childCategorys??>
                    <#list childCategorys as childCategory>
                        <li <#if  childCategoryId?? && childCategoryId == '${childCategory.categoryId?c}'>class="classifyBox2Active"</#if> data-id="tit1-${childCategory_index+2}" onclick="userClick(this,${category.categoryId?c},${childCategory.categoryId?c},selectType)">${childCategory.name}</li>
                    </#list>
                </#if>
            </ul>
        </div>
        <div class="newClear">
            <div class="classifyBoxTit classifyBoxTit2 <#if !isFull?? || isFull==''>classifyBox2Active</#if>" data-id="tit2-1" onclick="userClick2(selectType2)">不限</div>
            <ul class="classifyBoxUl classifyBoxUl2 newClear">
                <li data-id="tit2-2" <#if isFull?? && isFull=='1'>class="classifyBox2Active"</#if> onclick="userClick2(this,1,selectType2)">完结</li>
                <li data-id="tit2-3" <#if isFull?? && isFull=='0'>class="classifyBox2Active"</#if> onclick="userClick2(this,0,selectType2)">连载</li>
            </ul>
        </div>
    </div>
</div>
<div class="hr"></div>
<div  class="pd1Box pageLoad">
</#if>
    <#if pageFinder??>
        <#list pageFinder.data as book>
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
    </#if>
<#if syn=='0'>
</div>
<div class="bookLoad" id="autopbn" curpage="${pageFinder.pageNo+1}" totalpage="${pageFinder.pageCount}" rel="/portal/categoryBooks.go?page=${pageFinder.pageNo+1}&syn=1&categoryId=${categoryId}&childCategoryId=<#if childCategoryId??>${childCategoryId}</#if>&isFull=<#if isFull??>${isFull}</#if>" style="display:none;"></div>
<script type="text/javascript" src="/js/base.js"></script>
<script type="text/javascript" src="/js/autopage.js"></script>
<script type="text/javascript" src="/js/echo.min.js"></script>
<#include "../common/common.ftl"/>
<script type="text/javascript">
    $("#selectType").text($(".classifyBox2Active").eq(0).text());
    $("#selectType2").text($(".classifyBox2Active").eq(1).text());

    function bookInfo(bookId) {
        var url = "/book/bookDetail.go?bookId="+bookId;
        window.location.href=url;
    }

    $(function () {
        $(window).scroll(function () {
            var sum = $('#classifyBoxOne').height();
            var scroll = $(window).scrollTop();
            if (scroll > sum) {
                $('#classifyBoxTwo').css('top','0');
                $('.newClassifyBox2').hide();
                $('.newClassifyBox1').css('top','0');
            }
            if (scroll < sum) {
                $('#classifyBoxTwo').css('top','-41px');
                $('.newClassifyBox2').hide();
                $('.newClassifyBox1').css('top','-41px');
            }

            var s = $(window).scrollTop();
            if (s > 0) {
                $('.gotop').fadeIn(100);
            } else {
                $('.gotop').fadeOut(200);
            }
        })
    })
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