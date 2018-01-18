<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>渠道数据修改</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/css/amazeui.min.css" />
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/app.css?a=2">
    <script src="/js/calendar/WdatePicker.js"></script>
    <script type="text/javascript">
        function saveAndPublish(){
            $("#status").val(1);
            $('#activity_type_list_form').submit();
        }
    </script>
</head>
<body data-type="generalComponents">
<div class="tpl-page-container tpl-page-header-fixed">
<#include "../common/left.ftl"/>

    <div class="tpl-content-wrapper">
        <div class="tpl-portlet-components">
            <div class="tpl-block">
                <form action="/channelData/update.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="id" value="${channelData.id?c}">
                    <input type="hidden" name="channel" value="${channelData.channel?c}">
                    <input type="hidden" name="dau" value="${channelData.dau?c}">
                    <input type="hidden" name="money" value="${channelData.money?c}">
                    <input type="hidden" name="chargeNum" value="<#if channelData.chargeNum??>${channelData.chargeNum?c}</#if>">
                    <input type="hidden" name="status" id="status" value="0">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:10%; text-align:right">日期</td>
                            <td style="width:23%; text-align:left">${channelData.day}</td>
                            <td style="width:10%; text-align:right">渠道ID</td>
                            <td style="width:23%; text-align:left">${channelData.channel?c}</td>
                        </tr>
                        <tr>
                            <td style="width:10%; text-align:right">渠道名称</td>
                            <td style="width:23%; text-align:left">${channelData.channelName}</td>
                            <td style="width:10%; text-align:right">次日留存</td>
                            <td style="width:23%; text-align:left"><#if channelData.oneDayRetention??>${channelData.oneDayRetention}</#if></td>
                        </tr>
                        <tr>
                            <td style="width:10%; text-align:right">新增用户</td>
                            <td style="width:23%; text-align:left">
                                <#if type==3>
                                    <input type="text" style="width:80%;"  id="dnu" name="dnu" value="<#if channelData.dnu??>${channelData.dnu?c}</#if>">
                                <#else>
                                    <input type="hidden" name="dnu" value="${channelData.dnu?c}">
                                    ${channelData.dnu?c}
                                </#if>
                            </td>
                            <td style="width:10%; text-align:right">固定值</td>
                            <td style="width:23%; text-align:left">
                                <input type="text" style="width:80%;"  id="dnuFix" name="dnuFixed" value="<#if channelData.dnuFixed??>${channelData.dnuFixed?c}</#if>">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:10%; text-align:right">日活用户</td>
                            <td style="width:23%; text-align:left">${channelData.dau?c}</td>
                            <td style="width:10%; text-align:right">固定值</td>
                            <td style="width:23%; text-align:left">
                                <input type="text" style="width:80%;"  id="dauFix" name="dauFixed" value="<#if channelData.dauFixed??>${channelData.dauFixed?c}</#if>">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:10%; text-align:right">收入</td>
                            <td style="width:23%; text-align:left">${channelData.money?c}</td>
                            <td style="width:10%; text-align:right">固定值</td>
                            <td style="width:23%; text-align:left">
                                <input type="text" style="width:80%;"  id="moneyFix" name="moneyFixed" value="<#if channelData.moneyFixed??>${channelData.moneyFixed?c}</#if>">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:10%; text-align:right">充值次数</td>
                            <td style="width:23%; text-align:left"><#if channelData.chargeNum??>${channelData.chargeNum?c}</#if></td>
                            <td style="width:10%; text-align:right">&nbsp;</td>
                            <td style="width:23%; text-align:left">&nbsp;</td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="4">
                                <input type="button" onclick="saveAndPublish()" value="保存并发布">
                                <input type="submit" value="保存">
                                <input type="button" onclick="window.history.go(-1)" value="返回">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="tpl-alert"></div>
        </div>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/amazeui.min.js"></script>
<script src="/js/app.js"></script>
</body>
</html>