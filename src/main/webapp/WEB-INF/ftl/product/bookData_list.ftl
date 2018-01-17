<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>图书数据</title>
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
        function isNum(param) {
            var reg = new RegExp("^[0-9]*$");
            return reg.test(param);
        }

        function  search() {
            var channel = $("#channel").val();
            if(!isNum(channel)){
                alert("渠道ID只能为数字");
                return;
            }
            $('#activity_type_list_form').submit();
        }

        function exportData(){
            $("#activity_type_list_form").attr("action","/bookData/export.go");
            $("#activity_type_list_form").submit();
            $("#activity_type_list_form").attr("action","/bookData/list.go");
        }
    </script>
</head>
<body data-type="generalComponents">
<div class="tpl-page-container tpl-page-header-fixed">
    <#include "../common/left.ftl"/>

    <div class="tpl-content-wrapper">
        <div class="tpl-portlet-components">
            <div class="tpl-block">
                <form action="/bookData/list.go" method="post" id="activity_type_list_form" >
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:15%; text-align:right">作品名称</td>
                            <td style="width:30%; text-align:left"><input type="text" id="title" name="title" style="width: 80%" value="<#if condition.title??>${condition.title}</#if>"></td>
                            <td style="width:20%; text-align:right">渠道ID</td>
                            <td style="width:30%; text-align:left"><input type="text" id="channel" name="channel" style="width: 80%" value="<#if condition.channel??>${condition.channel}</#if>"></td>
                        </tr>
                        <tr>
                            <td style="width:15%; text-align:right">日期</td>
                            <td style="width:35%; text-align:left" colspan="3">
                                <input type="text" style="width: 150px;" type="text" name="startDate"  value="<#if condition??><#if condition.startDate??>${condition.startDate?substring(0,10)}</#if></#if>" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})" id="startDate" >
                                到
                                <input type="text" style="width: 150px;" type="text" name="endDate" class="Wdate" value="<#if condition??><#if condition.endDate??>${condition.endDate?substring(0,10)}</#if></#if>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})" id="endDate">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="4">
                                <input type="button" onclick="search()" value="查询">&nbsp;&nbsp;&nbsp;
                                <input type="button" onclick="exportData()" value="导出">
                            </td>
                        </tr>
                    </table>
                </form>

                <div class="am-g">
                    <#--<div class="am-u-sm-12">-->
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th style="width: 15%">日期</th>
                                    <th style="width: 20%">书名</th>
                                    <th style="width: 15%">渠道ID</th>
                                    <th style="width: 20%">渠道名称</th>
                                    <th style="width: 20%">收入（元）</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if data??>
                                    <#list data as bookData>
                                        <tr>
                                            <td>${bookData.cal}</td>
                                            <td>${bookData.title}</td>
                                            <td>
                                                <#if bookData.channel??>
                                                    ${bookData.channel?c}
                                                </#if>
                                            </td>
                                            <td>
                                                <#if bookData.channelName??>
                                                    ${bookData.channelName}
                                                </#if>
                                            </td>
                                            <td>${bookData.money/100.00}</td>
                                        </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                        </form>
                    <#--</div>-->
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