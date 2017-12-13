<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>账号分配</title>
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
        function  search() {
            $('#activity_type_list_form').submit();
        }

        function goPage(currentPage){
            $("#currentPage").val(currentPage);
            $('#activity_type_list_form').submit();
        }

        function deleteUser(userCmsId) {
            if(confirm("确定要删除吗？")){
                var url = "/userCms/delete.go?id=" + userCmsId;
                window.location.href = url;
            }
        }

        function toUpdate(userCmsId) {
            var url = "/userCms/toUpdate.go?id=" + userCmsId;
            window.location.href = url;
        }

        function toAdd() {
            var url = "/userCms/toAdd.go";
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
                <form action="/userCms/list.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="page" id="currentPage" value="1">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:15%; text-align:right">渠道名称</td>
                            <td style="width:35%; text-align:left"><input type="text" style="width: 80%" id="name" name="name" value="<#if condition.name??>${condition.name}</#if>"></td>
                            <td style="width:15%; text-align:right">是否内部</td>
                            <td style="width:35%; text-align:left">
                                <select name="adminFlag" style="width: 80%">
                                    <option value="">请选择</option>
                                    <option value="1" <#if condition.adminFlag?? && condition.adminFlag = 1>selected</#if>>管理员</option>
                                    <option value="0" <#if condition.adminFlag?? && condition.adminFlag = 0>selected</#if>>渠道</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:15%; text-align:right">操作时间</td>
                            <td style="width:35%; text-align:left" colspan="3">
                                <input type="text" style="width: 150px;" type="text" name="createDateStart" value="<#if condition??><#if condition.createDateStart??>${condition.createDateStart}</#if></#if>" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createDateEnd\')}'})" id="createDateStart" >
                                到
                                <input type="text" style="width: 150px;" type="text" name="createDateEnd" class="Wdate" value="<#if condition??><#if condition.createDateEnd??>${condition.createDateEnd}</#if></#if>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createDateStart\')}'})" id="createDateEnd">
                            </td>

                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="4">
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
                                    <th style="width: 20%">渠道名称</th>
                                    <th style="width: 15%">是否内部</th>
                                    <th style="width: 15%">登录名</th>
                                    <th style="width: 15%">登录密码</th>
                                    <th style="width: 15%">操作时间</th>
                                    <th style="width: 20%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageFinder?? && pageFinder.data??>
                                    <#list pageFinder.data as userCms>
                                        <tr>
                                            <td>${userCms.name}</td>
                                            <td>
                                                <#if userCms.adminFlag == 1>
                                                    管理员
                                                <#else>
                                                    渠道
                                                </#if>
                                            </td>
                                            <td class="am-hide-sm-only">${userCms.loginName}</td>
                                            <td class="am-hide-sm-only">${userCms.password}</td>
                                            <td class="am-hide-sm-only"><#if userCms.createDate??>${userCms.createDate?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">
                                                        <span class="am-icon-pencil-square-o" onclick="toUpdate(${userCms.id?c})">编辑</span>
                                                        <span class="am-icon-trash-o" onclick="deleteUser(${userCms.id?c})">删除</span>
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
</body>
</html>