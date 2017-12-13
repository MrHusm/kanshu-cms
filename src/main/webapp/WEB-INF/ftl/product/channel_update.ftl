<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>渠道修改</title>
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
        function save(){
            if(checkNull("channel")){
                PostbirdAlertBox.alert({'content': '请输入渠道ID'});
                return;
            }
            if(checkNull("channelName")){
                PostbirdAlertBox.alert({'content': '请输入渠道名称'});
                return;
            }
            if(checkNull("type")){
                PostbirdAlertBox.alert({'content': '请选择渠道类型'});
                return;
            }
            if(checkNull("dnuRatio")){
                PostbirdAlertBox.alert({'content': '请输入新增用户数量系数'});
                return;
            }
            if(checkNull("dauRatio")){
                PostbirdAlertBox.alert({'content': '请输入日活用户数量系数'});
                return;
            }
            if(checkNull("moneyRatio")){
                PostbirdAlertBox.alert({'content': '请输入收入系数'});
                return;
            }
            $('#activity_type_list_form').submit();
        }

        function checkNull(id){
            var value = $("#" + id).val()
            if(value == null || value == ''){
                return true;
            }else{
                return false;
            }
        }
    </script>
</head>
<body data-type="generalComponents">
<div class="tpl-page-container tpl-page-header-fixed">
<#include "../common/left.ftl"/>

    <div class="tpl-content-wrapper">
        <div class="tpl-portlet-components">
            <div class="tpl-block">
                <form action="/channel/update.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="id" value="${channel.id?c}">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:15%; text-align:right">渠道ID</td>
                            <td style="width:35%; text-align:left">
                                <input type="text" style="width:80%;"  id="channel" name="channel" value="<#if channel.channel??>${channel.channel?c}</#if>">
                            </td>
                            <td style="width:15%; text-align:right">渠道名称</td>
                            <td style="width:35%; text-align:left">
                                <input type="text" style="width:80%;"  id="channelName" name="channelName" value="<#if channel.channelName??>${channel.channelName}</#if>">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:15%; text-align:right">渠道类型</td>
                            <td style="width:35%; text-align:left">
                                <select name="type" id="type" style="width:80%">
                                    <option value="">请选择</option>
                                    <option value="1" <#if channel.type?? && channel.type = 1>selected</#if>>安卓渠道</option>
                                    <option value="2" <#if channel.type?? && channel.type = 2>selected</#if>>IOS渠道</option>
                                    <option value="3" <#if channel.type?? && channel.type = 3>selected</#if>>H5渠道</option>
                                </select>
                            </td>
                            <td style="width:15%; text-align:right">新增用户数量系数</td>
                            <td style="width:35%; text-align:left">
                                <input type="text" style="width:80%;"  id="dnuRatio" name="dnuRatio" value="<#if channel.dnuRatio??>${channel.dnuRatio}</#if>">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:15%; text-align:right">日活用户数量系数</td>
                            <td style="width:35%; text-align:left">
                                <input type="text" style="width:80%;"  id="dauRatio" name="dauRatio" value="<#if channel.dauRatio??>${channel.dauRatio}</#if>">
                            </td>
                            <td style="width:15%; text-align:right">收入系数</td>
                            <td style="width:35%; text-align:left">
                                <input type="text" style="width:80%;"  id="moneyRatio" name="moneyRatio" value="<#if channel.moneyRatio??>${channel.moneyRatio}</#if>">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="4">
                                <input type="button" onclick="save()" value="修改">&nbsp;&nbsp;&nbsp;
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
<script src="/js/postbirdAlertBox.js" ></script>
</body>
</html>