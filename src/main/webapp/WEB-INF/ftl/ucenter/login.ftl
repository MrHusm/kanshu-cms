<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/css/amazeui.min.css" />
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/app.css">
    <link rel="stylesheet" href="/css/postbirdAlertBox.css">
</head>

<body data-type="login">
<div class="am-g myapp-login">
    <div class="myapp-login-logo-block  tpl-login-max">
        <div class="myapp-login-logo-text">
            <div class="myapp-login-logo-text">
                阅享时代<!--<span> Login</span> <i class="am-icon-skyatlas"></i>-->
            </div>
        </div>
        <#--<div class="login-font">-->
            <#--<i>Login </i><!-- or <span> Sign Up&ndash;&gt;</span>-->
        <#--</div>-->
        <div class="am-u-sm-10 login-am-center">
            <form class="am-form"  id="login_form" method="post" action="/login/login.go">
                <fieldset>
                    <div class="am-form-group">
                        <input type="text" id="loginName" name="loginName" value="<#if userCms?? && userCms.loginName??>${userCms.loginName}</#if>" class="" id="doc-ipt-email-1" placeholder="请输入用户名">
                    </div>
                    <div class="am-form-group">
                        <input type="password" id="password" name="password" value="<#if userCms?? && userCms.password??>${userCms.password}</#if>" class="" id="doc-ipt-pwd-1" placeholder="请输入密码">
                    </div>
                    <p><button type="button" onclick="return login()" class="am-btn am-btn-default">登录</button></p>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/amazeui.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/postbirdAlertBox.js" ></script>
<script type="text/javascript">
    <#if errorMessage??>
        PostbirdAlertBox.alert({'content': '${errorMessage}'});
    </#if>
    function login(){
        var account = $('#loginName').val();
        if($.trim(account).length == 0){
            PostbirdAlertBox.alert({'content': '账号不能为空！'});
            return false;
        }
        var password = $('#password').val();
        if($.trim(password).length == 0){
            PostbirdAlertBox.alert({'content': '密码不能为空！'});
            return false;
        }
        $('#login_form').submit();
    }
</script>
</body>
</html>