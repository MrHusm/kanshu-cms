<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>渠道数据导出</title>

</head>
<body data-type="generalComponents">
<table border="1px">
    <thead>
    <tr>
        <th style="width: 10%">日期</th>
        <th style="width: 5%">渠道ID</th>
        <th style="width: 5%">渠道名称</th>
        <th style="width: 5%">新增用户</th>
        <th style="width: 6%">固定值</th>
        <th style="width: 5%">渠道显示</th>
        <th style="width: 5%">日活用户</th>
        <th style="width: 6%">固定值</th>
        <th style="width: 5%">渠道显示</th>
        <th style="width: 5%">收入</th>
        <th style="width: 6%">固定值</th>
        <th style="width: 5%">渠道显示</th>
        <th style="width: 5%">次日留存</th>
        <th style="width: 8%">状态</th>
    </tr>
    </thead>
    <tbody>
    <#if channelDatas??>
        <#list channelDatas as channelData>
        <tr>
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
            <td><#if channelData.oneDayRetention??>${channelData.oneDayRetention}</#if></td>
            <td>
                <#if channelData.status ==0>
                    未发布
                <#elseif channelData.status ==1>
                    已发布
                </#if>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>