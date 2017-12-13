<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>充值并购买</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="/js/jquery-2.1.0.min.js"></script>
</head>
<body>
<h1 id="time">正在充值(10)...</h1>
<script>
    var countdown=10;
    function isSuccess() {
        if (countdown == 0) {
            var url = "/user/userCenter.go";
            window.location.href=url;
        } else {
            $("#time").text("正在充值(" + countdown + ")...");
            var url = "/pay/isSuccess.go?orderNo=${orderNo}";
            $.ajax({
                url: url,
                type: 'GET',
                timeout: 10000,
                async:false,
                error: function(){},
                success: function(json){
                    var code = json.status.code;
                    if(code == 0){
                        var status = json.data.status;
                        if(status == 1){
                            var returnUrl = json.data.returnUrl;
                            window.location.href = returnUrl
                        }
                    }
                }
            });
            countdown--;
        }
        setTimeout(function(){ isSuccess() },1000);
    }
    isSuccess();

</script>
</body>
</html>