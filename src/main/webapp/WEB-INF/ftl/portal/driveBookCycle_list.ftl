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
        function  search() {
            $('#activity_type_list_form').submit();
        }

        function goPage(currentPage){
            $("#currentPage").val(currentPage);
            $('#activity_type_list_form').submit();
        }

        function deleteDriveBookCycle(id) {
            if(confirm("确定要删除吗？")){
                var url = "/driveBookCycle/delete.go?id=" + id;
                window.location.href = url;
            }
        }

        function toUpdate(id) {
            var url = "/driveBookCycle/toUpdate.go?id=" + id;
            window.location.href = url;
        }

        function toAdd() {
            var url = "/driveBookCycle/toAdd.go";
            window.location.href = url;
        }

        function toUpdateIdx(id){
            var btn="#btn_"+id;
            //var cancelBtn="#cancel_btn_"+id;
            var idx="#idx_"+id;
            var txt = "#txt_"+id;
            $(txt).hide();
            $(idx).show();
            $(btn).show();
            //$(cancelBtn).show();
        }
        function updateIdx(id){
            var idx="#idx_"+id;
            var url = '/driveBookCycle/updateScore.go?id='+id+'&score='+$(idx).val();
            window.location.href=url;
        }

        function checkAll(param){
            if($(param).is(':checked')){
                $("input[name='check']").prop("checked", true);
            }else{
                $("input[name='check']").prop("checked", false);
            }
        }

        function delsureBatch(){
            if(confirm("确认批量删除?")){
                var ids = "";
                var deleteFlag = false;
                $("input[name='check']").each(function(){
                    if($(this).is(':checked')){
                        ids += $(this).val()+",";
                        deleteFlag = true;
                    }
                });
                if(deleteFlag){
                    window.location.href="/driveBookCycle/batchDelete.go?ids="+ids;
                }else{
                    alert("请勾选需要删除的图书");
                }
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
                <form action="/driveBookCycle/list.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="page" id="currentPage" value="1">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:15%; text-align:right">榜单类型</td>
                            <td style="width:35%; text-align:left">
                                <select name="type" style="width: 80%">
                                    <option value="">请选择</option>
                                    <#list driveTypes as driveType>
                                        <option value="${driveType.type}" <#if condition.type?? && condition.type = driveType.type>selected</#if>>${driveType.name}</option>
                                    </#list>
                                </select>
                            </td>
                            <td style="width:15%; text-align:right">书名</td>
                            <td style="width:35%; text-align:left"><input type="text" id="bookName" name="bookName" value="<#if condition.bookName??>${condition.bookName}</#if>"></td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="4">
                                <input type="button" onclick="delsureBatch()" value="批量删除">&nbsp;&nbsp;&nbsp;
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
                                    <th style="width: 5%"><input type="checkbox" class="tpl-table-fz-check" onclick="checkAll(this)"></th>
                                    <th style="width: 10%">图书ID</th>
                                    <th style="width: 25%">书名</th>
                                    <th style="width: 10%">类型</th>
                                    <th style="width: 10%">开始时间</th>
                                    <th style="width: 10%">结束时间</th>
                                    <th style="width: 10%">排序</th>
                                    <th style="width: 10%">免费章节数</th>
                                    <th style="width: 16%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageFinder?? && pageFinder.data??>
                                    <#list pageFinder.data as driveBookCycle>
                                        <tr>
                                            <td><input type="checkbox" name="check" id="check" value="${driveBookCycle.id?c}"></td>
                                            <td>${driveBookCycle.bookId?c}</td>
                                            <td>${driveBookCycle.bookName}</td>
                                            <td>
                                                <#if driveBookCycle.type == 1>
                                                    首页驱动
                                                <#elseif driveBookCycle.type == 2>
                                                    男生最爱
                                                <#elseif driveBookCycle.type == 3>
                                                    女生频道
                                                <#elseif driveBookCycle.type == 4>
                                                    二次元
                                                <#elseif driveBookCycle.type == 5>
                                                    大家都在搜索
                                                <#elseif driveBookCycle.type == 6>
                                                    全站畅销
                                                <#elseif driveBookCycle.type == 7>
                                                    完结精选
                                                <#elseif driveBookCycle.type == 8>
                                                    重磅新书
                                                <#elseif driveBookCycle.type == 9>
                                                    全本免费
                                                <#elseif driveBookCycle.type == 11>
                                                    限章免费
                                                </#if>
                                            </td>
                                            <td><#if driveBookCycle.startDate??>${driveBookCycle.startDate?string("yyyy-MM-dd")}</#if></td>
                                            <td><#if driveBookCycle.endDate??>${driveBookCycle.endDate?string("yyyy-MM-dd")}</#if></td>
                                            <td>
                                                <span onclick="toUpdateIdx('${driveBookCycle.id?c}')" id="txt_${driveBookCycle.id?c }">${driveBookCycle.score }</span>
                                                <input type="text" id="idx_${driveBookCycle.id?c }" name="score" value="${driveBookCycle.score }" style="display:none;width:30%"/>
                                                <input type="button" id="btn_${driveBookCycle.id?c }" name="bt" style="display:none" value="修改" onclick="updateIdx('${driveBookCycle.id?c}')">
                                            </td>
                                            <td><#if driveBookCycle.num??>${driveBookCycle.num}</#if></td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">
                                                        <#--<span class="am-icon-pencil-square-o" onclick="toUpdate(${driveBookCycle.id?c})">编辑</span>-->
                                                        <span class="am-icon-trash-o" onclick="deleteDriveBookCycle(${driveBookCycle.id?c})">删除</span>
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