<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>榜单管理</title>
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
        function deleteDriveType(id) {
            if(confirm("该操作会同步删除该类型下的是所有图书，确定要删除吗？")){
                var url = "/driveType/delete.go?id=" + id;
                window.location.href = url;
            }
        }

        function toAdd() {
            var url = "/driveType/toAdd.go";
            window.location.href = url;
        }

        function toUpdate(id) {
            var url = "/driveType/toUpdate.go?id=" + id;
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
                <div class="am-g">
                    <div class="am-u-sm-12">
                        <input type="button" onclick="toAdd()" value="添加">
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th style="width: 20%">榜单类型</th>
                                <th style="width: 20%">榜单名称</th>
                                <th style="width: 40%">榜单描述</th>
                                <th style="width: 20%">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if data??>
                                <#list data as driveType>
                                    <tr>
                                        <td>${driveType.type?c}</td>
                                        <td>${driveType.name}</td>
                                        <td><#if driveType.comment??>${driveType.comment}</#if></td>
                                        <td>
                                            <div class="am-btn-toolbar">
                                                <div class="am-btn-group am-btn-group-xs">
                                                    <span class="am-icon-trash-o" onclick="toUpdate(${driveType.id?c})">修改</span>
                                                    <span class="am-icon-trash-o" onclick="deleteDriveType(${driveType.id?c})">删除</span>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
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
</body>
</html>