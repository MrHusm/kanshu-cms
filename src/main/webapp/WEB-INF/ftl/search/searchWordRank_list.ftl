<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>渠道</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/css/amazeui.min.css" />
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/app.css?a=2">
    <link rel="stylesheet" href="/css/postbirdAlertBox.css">
    <script src="/js/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
        function search() {
            $('#activity_type_list_form').submit();
        }

        function goPage(currentPage){
            $("#currentPage").val(currentPage);
            $('#activity_type_list_form').submit();
        }

        function deleteWord(id) {
            if(confirm("确定要删除吗？")){
                var url = "/searchWordRank/delete.go?id=" + id;
                window.location.href = url;
            }
        }

        function toUpdate(id) {
            var url = "/searchWordRank/toUpdate.go?id=" + id;
            window.location.href = url;
        }

        function toAdd() {
            var url = "/searchWordRank/toAdd.go";
            window.location.href = url;
        }
    </script>
</head>
<body data-type="generalComponents">
<div class="tpl-page-container tpl-page-header-fixed">
    <#include "../common/left.ftl"/>

    <div class="tpl-content-wrapper">
        <div class="tpl-portlet-components">
            <div class="tpl-block">
                <form action="/searchWordRank/list.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="page" id="currentPage" value="1">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:10%; text-align:right">搜索词</td>
                            <td style="width:23%; text-align:left"><input type="text" id="word" name="word" value="<#if condition.word??>${condition.word}</#if>"></td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="2">
                                <input type="button" onclick="toAdd()" value="添加">&nbsp;&nbsp;&nbsp;
                                <input type="button" onclick="search()" value="查询">
                            </td>
                        </tr>
                    </table>
                </form>

                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th style="width: 25%">搜索词</th>
                                    <th style="width: 50%">图书IDs</th>
                                    <th style="width: 25%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if searchWordRanks??>
                                    <#list searchWordRanks as searchWordRank>
                                        <tr>
                                            <td>${searchWordRank.word}</td>
                                            <td>${searchWordRank.bookIds}</td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">
                                                        <span class="am-icon-pencil-square-o" onclick="toUpdate(${searchWordRank.id?c})">编辑</span>
                                                        <span class="am-icon-trash-o" onclick="deleteWord(${searchWordRank.id?c})">删除</span>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                            <hr>
                        </form>
                    </div>
                </div>
            </div>
            <div class="tpl-alert"></div>
        </div>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/amazeui.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/postbirdAlertBox.js" ></script>
</body>
</html>