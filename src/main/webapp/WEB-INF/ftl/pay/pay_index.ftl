<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" class="newBg" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>充值</title>
    <link rel="stylesheet" href="/css/reset_5.css">
    <script>
        function reacharOne(el) {
            $('.reachargeWayBox1').removeClass('wayBoxActive').removeClass('wayBoxActive2');
            var text = $(el).text();
            if ($.trim(text) == '微信支付') {
                $("#pay").attr("name",'1');
                $(el).addClass('wayBoxActive')
            }else if($.trim(text) == '支付宝支付'){
                $("#pay").attr("name",'2');
                $(el).addClass('wayBoxActive2')
            }
        }
        function reacharTwo(el) {
            $('.wayMoneyBox1').removeClass('wayMoneyBox1Active');
            $(el).addClass('wayMoneyBox1Active');
        }
    </script>
</head>
<body class="newBg">
<#include "../common/head.ftl"/>
<div class="bookHeader">
    <img src="/images/icon/backing.png" alt="" onclick="window.history.go(-1)" class="headerImg left">
    <div class="headerMidden">充值</div>
    <img src="/images/icon/menu.png" alt="" class="headerImg right" onclick="twoLevelNavToggle()">
</div>
<div class="rechargeCcount">
    <div class="rechargeCcount1">账户：${user.name}</div>
    <div>余额：${(userAccount.money + userAccount.virtualMoney)?c}钻</div>
    <button class="downApp rechargeDown" style="display: none" id="downBanner" onclick="downloadApp('${channel}')">
            <span class="downAppSpan">
                下载官方App
                <br>
                全站图书免费读
            </span>
        <img src="/images/icon/download2.png" alt="" class="downAppIcon">
    </button>
</div>
<div class="hr"></div>
<div class="reachargeWay">
    <div class="reachargeWay1">
        <#--<div class="reachargeWayBox1 wayBoxActive" id="weixin" onclick="reacharOne(this)">-->
            <#--<img class="reachargeWayImg" src="/images/icon/weixin.png" alt="" />-->
            <#--<span>微信支付</span>-->
        <#--</div>-->
        <div class="reachargeWayBox1 wayBoxActive2" id="alipay" onclick="reacharOne(this)">
            <img class="reachargeWayImg" src="/images/icon/zhifubaoPay.png" alt="" />
            <span>支付宝支付</span>
        </div>
    </div>
    <div class="chapterBuy recharge">
        <div class="chapterBuyTitBg"></div>
        <div class="chapterBuyTit">请选择充值金额</div>
        <div class="chapterBuyTitBg"></div>
    </div>
<#if rechargeItems??>
    <div class="wayMoney">
        <#list rechargeItems as rechargeItem>
            <#if rechargeItem_index % 2 ==0>
                <div class="wayMoneyBox">
                    <div class="wayMoneyBox1 <#if rechargeItem_index ==0>wayMoneyBox1Active</#if>" id="${rechargeItem.rechargeItemId}" onclick="reacharTwo(this)">
                        <div class="fz16">${rechargeItem.price?c}元</div>
                        <#if rechargeItem.virtual?? && rechargeItem.virtual != 0>
                            <div class="fz12">${rechargeItem.money?c}钻+${rechargeItem.virtual?c}钻</div>
                            <div class="wayMoneyBoxTag">
                                <sapn class="wayMoneyBoxTagTxt">赠<br>${((rechargeItem.virtual / rechargeItem.money)*100)?string('#')}%</sapn>
                            </div>
                        <#else>
                            <div class="fz12">${rechargeItem.money?c}钻</div>
                        </#if>
                    </div>
            <#else>
                    <div class="wayMoneyBox1" id="${rechargeItem.rechargeItemId}" onclick="reacharTwo(this)">
                        <div class="fz16">${rechargeItem.price?c}元</div>
                        <#if rechargeItem.virtual?? && rechargeItem.virtual != 0>
                            <div class="fz12">${rechargeItem.money?c}钻+${rechargeItem.virtual?c}钻</div>
                            <div class="wayMoneyBoxTag">
                                <sapn class="wayMoneyBoxTagTxt">赠<br>${((rechargeItem.virtual / rechargeItem.money)*100)?string('#')}%</sapn>
                            </div>
                        <#else>
                            <div class="fz12">${rechargeItem.money?c}钻</div>
                        </#if>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
</#if>
    <input class="btn chapterBtn rechargeBtn" onclick="pay()" name="2" id="pay" type="button" value="下一步">
</div>
<#include "../common/common.ftl"/>
<script>
    if (navigator.userAgent.match(/Android/i)) {
        $("#downBanner").show()
    }

    function downloadApp(channel){
        var url = "http://quanshunjt.com/doc/app-channel100000-release.apk";
        if(channel == '300001'){
            url = "http://quanshunjt.com/doc/app-channel100015-release.apk";
        }else if(channel == '300002'){
            url = "http://quanshunjt.com/doc/app-channel100016-release.apk";
        }else if(channel == '300003'){
            url = "http://quanshunjt.com/doc/app-channel100017-release.apk";
        }else if(channel == '300004'){
            url = "http://quanshunjt.com/doc/app-channel100018-release.apk";
        }else if(channel == '300005'){
            url = "http://quanshunjt.com/doc/app-channel100019-release.apk";
        }else if(channel == '300006'){
            url = "http://quanshunjt.com/doc/app-channel100020-release.apk";
        }else if(channel == '300007'){
            url = "http://quanshunjt.com/doc/app-channel100021-release.apk";
        }else if(channel == '300008'){
            url = "http://quanshunjt.com/doc/app-channel100022-release.apk";
        }else if(channel == '300009'){
            url = "http://quanshunjt.com/doc/app-channel100023-release.apk";
        }else if(channel == '300010'){
            url = "http://quanshunjt.com/doc/app-channel100024-release.apk";
        }else if(channel == '300011'){
            url = "http://quanshunjt.com/doc/app-channel100025-release.apk";
        }
        window.open(url);
    }

    function pay(){
        if($("#pay").attr("name") == '1'){
            var rechargeItemId = $(".wayMoneyBox1Active").attr("id");
            window.location.href = "/weixin/order.go?type=2&productId="+rechargeItemId;
        }else{
            var rechargeItemId = $(".wayMoneyBox1Active").attr("id");
            window.location.href = "/alipay/order.go?type=1&productId="+rechargeItemId;
        }
    }
</script>
</body>
</html>