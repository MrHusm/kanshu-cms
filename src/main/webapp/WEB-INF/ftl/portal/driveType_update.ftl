<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>榜单添加</title>
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
            if(checkNull("name")){
                PostbirdAlertBox.alert({'content': '请输入榜单名称'});
                return;
            }
            if(checkNull("type")){
                PostbirdAlertBox.alert({'content': '请选择榜单类型'});
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
                <form action="/driveType/update.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="id" value="${driveType.id?c}">
                    <input type="hidden" name="type" id="type" value="${driveType.type?c}">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:15%; text-align:right">榜单类型</td>
                            <td style="width:35%; text-align:left">${driveType.type}</td>
                            <td style="width:15%; text-align:right">榜单名称</td>
                            <td style="width:35%; text-align:left">
                                <input type="text" id="name" name="name" value="${driveType.name}">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:15%; text-align:right">榜单描述</td>
                            <td style="width:85%; text-align:left" colspan="3">
                                <input type="text" id="comment" name="comment" style="width: 80%" value="<#if driveType.comment??>${driveType.comment}</#if>">
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