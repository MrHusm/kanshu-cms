<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>渠道数据</title>
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
        function  search() {
            $('#activity_type_list_form').submit();
        }

        function goPage(currentPage){
            $("#currentPage").val(currentPage);
            $('#activity_type_list_form').submit();
        }

        function toUpdate(channelDataId) {
            var url = "/channelData/toUpdate.go?id=" + channelDataId;
            window.location.href = url;
        }

        function toAdd() {
            var url = "/channelData/toAdd.go";
            window.location.href = url;
        }

        function checkAll(param){
            if($(param).is(':checked')){
                $("input[name='channelDataItem']").prop("checked", true);
            }else{
                $("input[name='channelDataItem']").prop("checked", false);
            }
        }
        
        function  publish() {
            var ids = "";
            $("input[name='channelDataItem']").each(function(){
                if($(this).is(':checked')){
                    ids += $(this).val() + ",";
                }
            });
            if(ids != ''){
                var url = "/channelData/publish.go?ids=" + ids;
                window.location.href = url;
            }else{
                PostbirdAlertBox.alert({'content': '请选择需要发布的数据！'});
            }
        }

        function sortData(orderColumn){
            var orderColumnOld = $("#orderColumn").val();
            var orderType = $("#orderType").val();
            if(orderColumnOld == orderColumn){
                if(orderType == '1'){
                    $("#orderType").val('0');
                }else{
                    $("#orderType").val('1');
                }
            }else{
                $("#orderColumn").val(orderColumn);
                $("#orderType").val('1');
            }
            $('#activity_type_list_form').submit();
        }

        function exportData(){
            $("#activity_type_list_form").attr("action","/channelData/export.go");
            $("#activity_type_list_form").submit();
            $("#activity_type_list_form").attr("action","/channelData/list.go");
        }
    </script>
</head>
<body data-type="generalComponents">
<div class="tpl-page-container tpl-page-header-fixed">
    <#include "../common/left.ftl"/>

    <div class="tpl-content-wrapper">
        <div class="tpl-portlet-components">
            <div class="tpl-block">
                <form action="/channelData/list.go" method="post" id="activity_type_list_form" >
                    <input type="hidden" name="page" id="currentPage" value="1">
                    <input type="hidden" name="orderColumn" id="orderColumn" value="<#if condition.orderColumn??>${condition.orderColumn}</#if>">
                    <input type="hidden" name="orderType" id="orderType" value="<#if condition.orderType??>${condition.orderType}</#if>">
                    <table class="am-table  table-main">
                        <tr>
                            <td style="width:10%; text-align:right">渠道ID（多个小逗号隔开）</td>
                            <td style="width:35%; text-align:left"><input type="text" id="channelsOne" name="channelsOne" value="<#if condition.channelsOne??>${condition.channelsOne}</#if>"></td>
                            <td style="width:15%; text-align:right">日期</td>
                            <td style="width:35%; text-align:left">
                                <input type="text" style="width: 150px;" type="text" name="dayStart"  value="<#if condition??><#if condition.dayStart??>${condition.dayStart}</#if></#if>" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'dayEnd\')}'})" id="dayStart" >
                                到
                                <input type="text" style="width: 150px;" type="text" name="dayEnd" class="Wdate" value="<#if condition??><#if condition.dayEnd??>${condition.dayEnd}</#if></#if>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'dayStart\')}'})" id="dayEnd">
                            </td>
                        </tr>
                        <tr>
                            <td style="width:100%; text-align:center" colspan="4">
                                <input type="button" onclick="publish()" value="发布">&nbsp;&nbsp;
                                <input type="button" onclick="search()" value="查询">&nbsp;&nbsp;
                                <input type="button" onclick="exportData()" value="导出">
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
                                    <th style="width: 3%"><input type="checkbox" class="tpl-table-fz-check" onclick="checkAll(this)"></th>
                                    <th style="width: 10%" onclick="sortData('day')">日期</th>
                                    <th style="width: 5%" onclick="sortData('channel')">渠道ID</th>
                                    <th style="width: 5%">渠道名称</th>
                                    <th style="width: 5%" onclick="sortData('dnu')">新增用户</th>
                                    <th style="width: 6%">固定值</th>
                                    <th style="width: 5%">渠道显示</th>
                                    <th style="width: 5%" onclick="sortData('dau')">日活用户</th>
                                    <th style="width: 6%">固定值</th>
                                    <th style="width: 5%">渠道显示</th>
                                    <th style="width: 5%" onclick="sortData('money')">收入</th>
                                    <th style="width: 6%">固定值</th>
                                    <th style="width: 5%">渠道显示</th>
                                    <th style="width: 5%">充值次数</th>
                                    <th style="width: 5%" onclick="sortData('one_day_retention')">次日留存</th>
                                    <th style="width: 8%">状态</th>
                                    <th style="width: 8%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageFinder?? && pageFinder.data??>
                                    <#list pageFinder.data as channelData>
                                        <tr>
                                            <td>
                                                <#if channelData.status ==0>
                                                    <input type="checkbox" name="channelDataItem" value="${channelData.id?c}">
                                                </#if>
                                            </td>
                                            <td>${channelData.day}</td>
                                            <td>${channelData.channel?c}</td>
                                            <td>${channelData.channelName}</td>
                                            <td>${channelData.dnu?c}</td>
                                            <td><#if channelData.dnuFixed??>${channelData.dnuFixed?c}</#if></td>
                                            <td>${channelData.dnuShow?c}</td>
                                            <td>${channelData.dau?c}</td>
                                            <td><#if channelData.dauFixed??>${channelData.dauFixed?c}</#if></td>
                                            <td>${channelData.dauShow?c}</td>
                                            <td>${channelData.money?c}</td>
                                            <td><#if channelData.moneyFixed??>${channelData.moneyFixed?c}</#if></td>
                                            <td>${channelData.moneyShow?c}</td>
                                            <td><#if channelData.chargeNum??>${channelData.chargeNum?c}</#if></td>
                                            <td><#if channelData.oneDayRetention??>${channelData.oneDayRetention}</#if></td>
                                            <td>
                                                <#if channelData.status ==0>
                                                    未发布
                                                <#elseif channelData.status ==1>
                                                    已发布
                                                </#if>
                                            </td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">
                                                        <#if channelData.status ==0>
                                                            <span class="am-icon-pencil-square-o" onclick="toUpdate(${channelData.id?c})">编辑</span>
                                                        </#if>
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
<script src="/js/postbirdAlertBox.js" ></script>
</body>
</html>