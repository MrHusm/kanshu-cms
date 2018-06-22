package com.yxsd.kanshu.ucenter.service;


import com.yxsd.kanshu.base.service.IBaseService;
import com.yxsd.kanshu.ucenter.model.UserWelfare;

/**
 * Created by hushengmeng on 2018/1/9.
 */
public interface IUserWelfareService extends IBaseService<UserWelfare,Long> {

    /**
     * 获取新用户福利类型
     * @return 1：领VIP 2：签到
     */
    Integer getUserWelfareType();
}
