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
            if(checkNull("bookIds")){
                PostbirdAlertBox.alert({'content': '请输入图书ID'});
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

        function showNum(){
            if($("#type").val() == 11){
                $("#numTr").show();
            }else{
                $("#numTr").hide();
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
                <form action="/driveBook/add.go" method="post" id="activity_type_list_form" >
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:25%; text-align:right">图书ID</td>
                            <td style="width:75%; text-align:left">
                                <textarea name="bookIds" id="bookIds" rows="3" cols="80"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:25%; text-align:right">榜单类型</td>
                            <td style="width:75%; text-align:left">
                                <select name="type" id="type" onchange="showNum()" style="width: 50%">
                                    <option value="">请选择</option>
                                    <#list driveTypes as driveType>
                                        <option value="${driveType.type}">${driveType.name}</option>
                                    </#list>
                                </select>
                            </td>
                        </tr>
                        <tr style="display: none;" id="numTr">
                            <td style="width:25%; text-align:right">图书限免章节数</td>
                            <td style="width:75%; text-align:left">
                                <input type="text" name="num">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="2">
                                <input type="button" onclick="save()" value="保存">&nbsp;&nbsp;&nbsp;
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