<div class="tpl-left-nav tpl-left-nav-hover">
    <div class="tpl-left-nav-title">
        春意小说数据后台
    </div>
    <div class="tpl-left-nav-list">
        <ul class="tpl-left-nav-menu">
            <li class="tpl-left-nav-item">
                <!-- 打开状态 a 标签添加 active 即可   -->
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list active">
                    <i class="am-icon-table"></i>
                    <span>数据管理</span>
                    <!-- 列表打开状态的i标签添加 tpl-left-nav-more-ico-rotate 图表即90°旋转  -->
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu" style="display:block">
                    <li>
                        <!-- 打开状态 a 标签添加 active 即可   -->
                        <a href="/channelData/list.go">
                            <i class="am-icon-angle-right"></i>
                            <span>渠道数据</span>
                            <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                        </a>
                    <#if Session.userSessionInfo.adminFlag = 1>
                        <a href="/userCms/list.go">
                            <i class="am-icon-angle-right"></i>
                            <span>账号分配</span>
                            <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                        </a>
                        <a href="/channel/list.go">
                            <i class="am-icon-angle-right"></i>
                            <span>渠道</span>
                            <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                        </a>
                    </#if>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>