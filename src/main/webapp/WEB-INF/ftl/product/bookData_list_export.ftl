<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>图书数据</title>
</head>
<body data-type="generalComponents">
    <table border="1px">
        <thead>
        <tr>
            <th style="width: 15%">日期</th>
            <th style="width: 20%">书名</th>
            <th style="width: 15%">渠道ID</th>
            <th style="width: 20%">渠道名称</th>
            <th style="width: 20%">收入（元）</th>
        </tr>
        </thead>
        <tbody>
        <#if data??>
            <#list data as bookData>
                <tr>
                    <td>${bookData.cal}</td>
                    <td>${bookData.title}</td>
                    <td>
                        <#if bookData.channel??>
                            ${bookData.channel?c}
                        </#if>
                    </td>
                    <td>
                        <#if bookData.channelName??>
                            ${bookData.channelName}
                        </#if>
                    </td>
                    <td>${bookData.money/100.00}</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</body>
</html>