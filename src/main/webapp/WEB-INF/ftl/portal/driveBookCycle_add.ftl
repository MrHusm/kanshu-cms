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
            if(checkNull("startDateStr")){
                PostbirdAlertBox.alert({'content': '请输入轮播时间'});
                return;
            }
            if(checkNull("endDateStr")){
                PostbirdAlertBox.alert({'content': '请输入轮播时间'});
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
                <form action="/driveBookCycle/add.go" method="post" id="activity_type_list_form" >
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
                                <select name="type" id="type" style="width: 50%">
                                    <option value="">请选择</option>
                                    <option value="1">首页驱动</option>
                                    <option value="2">男生最爱</option>
                                    <option value="3">女生频道</option>
                                    <option value="4">二次元</option>
                                    <option value="5">大家都在搜索</option>
                                    <option value="6">全站畅销</option>
                                    <option value="7">完结精选</option>
                                    <option value="8">重磅新书</option>
                                    <option value="9">免费</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:25%;text-align:right">轮播时间</td>
                            <td style="width:75%;text-align:left">
                                <input type="text" style="width: 150px;" type="text" name="startDateStr" value="" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDateStr\')}'})" id="startDateStr" >
                                到
                                <input type="text" style="width: 150px;" type="text" name="endDateStr" class="Wdate" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDateStr\')}'})" id="endDateStr">
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