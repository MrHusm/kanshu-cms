<script src="/js/baidu-statis.js"></script>
<script>
    function twoLevelNavToggle() {
        $('.newTwoLevelNavBox').toggle();
        if($('.newTwoLevelNavBox').is(':visible')){
            $("body").on("touchmove",function(event){
                event.preventDefault;
            }, false)
        }else{
            $("body").off("touchmove");
        }
    }

    function rankList(type){
        var url = "/portal/rankList.go?type="+type;
        window.location.href = url;
    }

    function goCategory(){
        var url = "/portal/categoryIndex.go";
        window.location.href = url;
    }
</script>
<div class="newTwoLevelNavBox" hidden>
    <div class="indexNav mt0">
        <img src="/images/icon/close.png" alt="" class="newTwoLevelNavBoxClose" onclick="twoLevelNavToggle()">
        <div class="indexNavBox">
            <div onclick="rankList(9)">
                <img src="/images/icon/indexIcon1.png" alt="" class="indexFree">
                <div>免费</div>
            </div>
            <div onclick="goCategory()">
                <img src="/images/icon/indexIcon2.png" alt="" class="indexFree">
                <div>分类</div>
            </div>
            <div onclick="rankList(7)">
                <img src="/images/icon/indexIcon3.png" alt="" class="indexFree">
                <div>完本</div>
            </div>
            <div onclick="rankList(8)">
                <img src="/images/icon/indexIcon4.png" alt="" class="indexFree">
                <div>新书</div>
            </div>
        </div>
        <div class="indexNavBox">
            <div onclick="rankList(6)">
                <img src="/images/icon/indexIcon5.png" alt="" class="indexFree">
                <div>热销</div>
            </div>
            <div onclick="rankList(2)">
                <img src="/images/icon/indexIcon6.png" alt="" class="indexFree">
                <div>男生</div>
            </div>
            <div onclick="rankList(3)">
                <img src="/images/icon/indexIcon7.png" alt="" class="indexFree">
                <div>女生</div>
            </div>
            <div onclick="rankList(4)">
                <img src="/images/icon/indexIcon8.png" alt="" class="indexFree">
                <div>二次元</div>
            </div>
        </div>
        <div class="twoLevelNav">
            <div onclick="javascript:window.location.href='/portal/portalIndex.go'">
                <img src="/images/icon/home.png" alt="" class="twoLevelNavImg" />
                <span>首页</span>
            </div>
            <i></i>
            <div onclick="javascript:window.location.href='/user/queryUserShelf.go'">
                <img src="/images/icon/bookshelfIcon.png" alt="" class="twoLevelNavImg" />
                <span>书架</span>
            </div>
        </div>
    </div>
    <div style="height: 100%" onclick="twoLevelNavToggle()"></div>
</div>
