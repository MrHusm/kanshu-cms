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
            <th style="width: 10%">渠道ID</th>
            <th style="width: 15%">渠道名称</th>
            <th style="width: 10%" >新增用户</th>
            <th style="width: 10%" >日活用户</th>
            <th style="width: 10%" >收入</th>
            <th style="width: 10%" >充值次数</th>
            <th style="width: 10%">次日留存</th>
            <#if Session.userSessionInfo.channels?index_of("300022")!=-1>
                <th>总PV</th>
                <th>总UV</th>
                <th>充值唤起数</th>
            </#if>
        </tr>
        </thead>
        <tbody>
        <#if channelDatas??>
            <#list channelDatas as channelData>
                <tr>
                    <td>${channelData.day}</td>
                    <td>${channelData.channel?c}</td>
                    <td>${channelData.channelName}</td>
                    <td>${channelData.dnuShow?c}</td>
                    <td>${channelData.dauShow?c}</td>
                    <td>${channelData.moneyShow?c}</td>
                    <td><#if channelData.chargeNum??>${channelData.chargeNum?c}</#if></td>
                    <td><#if channelData.oneDayRetention??>${channelData.oneDayRetention}</#if></td>
                    <#if Session.userSessionInfo.channels?index_of("300022")!=-1>
                        <td><#if channelData.pv??>${channelData.pv?c}</#if></td>
                        <td><#if channelData.uv??>${channelData.uv?c}</#if></td>
                        <td><#if channelData.payOrderNum??>${channelData.payOrderNum?c}</#if></td>
                    </#if>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>

</body>
</html>