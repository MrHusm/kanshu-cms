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
        function  search() {
            $('#activity_type_list_form').submit();
        }

        function goPage(currentPage){
            $("#currentPage").val(currentPage);
            $('#activity_type_list_form').submit();
        }

        function deleteChannel(channelId) {
            if(confirm("确定要删除吗？")){
                var url = "/channel/delete.go?id=" + channelId;
                window.location.href = url;
            }
        }

        function toUpdate(channelId) {
            var url = "/channel/toUpdate.go?id=" + channelId;
            window.location.href = url;
        }

        function toAdd() {
            var url = "/channel/toAdd.go";
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
                <form action="/channel/list.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="page" id="currentPage" value="1">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:10%; text-align:right">渠道ID</td>
                            <td style="width:23%; text-align:left"><input type="text" id="channels" name="channels" value="<#if condition.channels??>${condition.channels}</#if>"></td>
                            <td style="width:10%; text-align:right">渠道名称</td>
                            <td style="width:23%; text-align:left">
                                <input type="text" id="channelName" name="channelName" value="<#if condition.channelName??>${condition.channelName}</#if>">
                            </td>
                            <td style="width:10%; text-align:right">渠道类型</td>
                            <td style="width:23%; text-align:left">
                                <select name="type" id="type" style="width:50%">
                                    <option value="">请选择</option>
                                    <option value="1" <#if condition.type?? && condition.type = 1>selected</#if>>安卓渠道</option>
                                    <option value="2" <#if condition.type?? && condition.type == 2>selected</#if>>IOS渠道</option>
                                    <option value="3" <#if condition.type?? && condition.type = 3>selected</#if>>H5渠道</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="6">
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
                                    <th style="width: 10%">渠道ID</th>
                                    <th style="width: 10%">渠道名称</th>
                                    <th style="width: 10%">渠道类型</th>
                                    <th style="width: 10%">新增用户数量系数</th>
                                    <th style="width: 10%">日活用户数量系数</th>
                                    <th style="width: 10%">收入系数</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageFinder?? && pageFinder.data??>
                                    <#list pageFinder.data as channel>
                                        <tr>
                                            <td>${channel.channel?c}</td>
                                            <td>${channel.channelName}</td>
                                            <td>
                                                <#if channel.type == 1>
                                                    安卓渠道
                                                <#elseif channel.type == 2>
                                                    IOS渠道
                                                <#elseif channel.type == 3>
                                                    H5渠道
                                                </#if>
                                            </td>
                                            <td>${channel.dnuRatio}</td>
                                            <td>${channel.dauRatio}</td>
                                            <td>${channel.moneyRatio}</td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">
                                                        <span class="am-icon-pencil-square-o" onclick="toUpdate(${channel.id?c})">编辑</span>
                                                        <span class="am-icon-trash-o" onclick="deleteChannel(${channel.id?c})">删除</span>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                            <div class="am-cf">
                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">
                                        <#if pageFinder.pageNo != 1>
                                            <li class=""><a href="#" onclick="goPage(${pageFinder.pageNo-1})">«</a></li>
                                        </#if>
                                        <li class="am-active"><a href="#">${pageFinder.pageNo}</a></li>
                                        <#if (pageFinder.pageNo + 1) lte pageFinder.pageCount>
                                            <li class=""><a href="#" onclick="goPage(${pageFinder.pageNo + 1})">${pageFinder.pageNo + 1}</a></li>
                                        </#if>
                                        <#if (pageFinder.pageNo + 2) lte pageFinder.pageCount>
                                            <li class=""><a href="#" onclick="goPage(${pageFinder.pageNo + 2})">${pageFinder.pageNo + 2}</a></li>
                                        </#if>
                                        <#if (pageFinder.pageNo + 3) lte pageFinder.pageCount>
                                            <li class=""><a href="#" onclick="goPage(${pageFinder.pageNo + 3})">${pageFinder.pageNo + 3}</a></li>
                                        </#if>
                                        <#if (pageFinder.pageNo + 4) lte pageFinder.pageCount>
                                            <li class=""><a href="#" onclick="goPage(${pageFinder.pageNo + 4})">${pageFinder.pageNo + 4}</a></li>
                                        </#if>
                                        <#if (pageFinder.pageNo + 5) lte pageFinder.pageCount>
                                            <li class=""><a href="#" onclick="goPage(${pageFinder.pageNo + 5})">${pageFinder.pageNo + 5}</a></li>
                                        </#if>
                                        <#if pageFinder.pageNo != pageFinder.pageCount && pageFinder.pageCount !=0>
                                            <li><a href="#" onclick="goPage(${pageFinder.pageNo + 1})">»</a></li>
                                        </#if>
                                    </ul>
                                </div>
                            </div>
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