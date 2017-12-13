<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>分类</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="/css/reset_5.css">
</head>
<body>
    <#include "../common/head.ftl"/>
    <div class="bookHeader">
        <img src="/images/icon/backing.png" alt="" class="headerImg left" onclick="window.history.go(-1)">
        <div class="headerMidden">分类</div>
        <img src="/images/icon/menu.png" alt="" class="headerImg right" onclick="twoLevelNavToggle()">
    </div>

    <#if data??>
        <#list data as map>
        <#list map?keys as key>
            <section class="freeDivision newFreeDivision">
                <div class="h6"><i class="h6Icon"></i>
                    <#if key == '男'>男频
                    <#elseif key == '女'>女频
                    <#elseif key == '其他'>出版物
                    <#else>${key}
                    </#if>
                </div>
                <div class="tag newClear">
                    <#list map[key] as category>
                        <div class="tagPub"><span class="tagCont tagPubStyle${(map_index % 4) + 1}" onclick="categoryBooks(${category.categoryId?c},'${category.name}')">${category.name}</span></div>
                    </#list>
                </div>
            </section>
       </#list>
        </#list>
    </#if>
<#include "../common/common.ftl"/>
<script>
    function categoryBooks(categoryId){
        var url = "/portal/categoryBooks.go?categoryId="+categoryId;
        window.location.href = url;
    }
</script>
</body>
</html>