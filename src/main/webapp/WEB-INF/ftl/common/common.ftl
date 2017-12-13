<#--<link rel="stylesheet" href="/css/nprogress.css">-->
<script src="/js/jquery-2.1.0.min.js"></script>
<#--<script src="/js/nprogress.js"></script>-->
<script>
    // 加载的进度条
//    NProgress.configure({
//        template: $('#myId').html() // template是用来设置动画样式的属性
//    });
//    NProgress.start();
//
//    $(window).load(function(){
//        NProgress.done();
//    });

    // 返回顶部
    $(function(){
        $('.gotop').click(function(){
            $('html,body').animate({scrollTop:0},500);
        });
    })

    function showScroll(){
        $(window).scroll(function () {
            var s = $(window).scrollTop();
            if (s > 0) {
                $('.gotop').fadeIn(100);
            } else {
                $('.gotop').fadeOut(200);
            }
        });
    }
    showScroll();
</script>
<#--<script type="text" id="myId">-->
    <#--<em id="__mceDel"><em id="__mceDel"><div class="bar barOne" role="bar" style="display:block;background:#01b11a"></div></em></em>-->
<#--</script>-->

<img src="/images/icon/goTop.png" alt="" class="goTopImg gotop" />


