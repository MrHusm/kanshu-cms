<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>充值明细</title>
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

        function goPage(currentPage){
            $("#currentPage").val(currentPage);
            $('#activity_type_list_form').submit();
        }

        function  search() {
            var channel = $("#channel").val();
            if(!isNum(channel)){
                alert("渠道ID只能为数字");
                return;
            }
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
                <form action="/user/list.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="page" id="currentPage" value="1">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:15%; text-align:right">渠道ID</td>
                            <td style="width:35%; text-align:left"><input type="text" id="channel" name="channel" style="width: 80%" value="<#if condition.channel??>${condition.channel}</#if>"></td>
                            <td style="width:15%; text-align:right">日期</td>
                            <td style="width:35%;">
                                <input type="text" style="width: 150px;" type="text" name="startDate"  value="<#if condition??><#if condition.startDate??>${condition.startDate?substring(0,10)}</#if></#if>" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})" id="startDate" >
                                到
                                <input type="text" style="width: 150px;" type="text" name="endDate" class="Wdate" value="<#if condition??><#if condition.endDate??>${condition.endDate?substring(0,10)}</#if></#if>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})" id="endDate">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="4">
                                <input type="button" onclick="search()" value="查询">&nbsp;&nbsp;&nbsp;
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
                                    <th style="width: 15%">用户ID</th>
                                    <th style="width: 20%">订单号</th>
                                    <th style="width: 10%">渠道号</th>
                                    <th style="width: 15%">充值金额（元）</th>
                                    <th style="width: 20%">充值方式</th>
                                    <th style="width: 20%">日期</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageFinder?? && pageFinder.data??>
                                    <#list pageFinder.data as userAccountLog>
                                        <tr>
                                            <td>${userAccountLog.userId?c}</td>
                                            <td>${userAccountLog.orderNo}</td>
                                            <td>${userAccountLog.channel?c}</td>
                                            <td>${userAccountLog.comment}</td>
                                            <td>
                                                <#if userAccountLog.type == 1>
                                                    支付宝充值
                                                <#elseif userAccountLog.type == 2>
                                                    微信充值
                                                </#if>
                                            </td>
                                            <td>${userAccountLog.createDate?string("yyyy-MM-dd HH:mm:ss")}</td>
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