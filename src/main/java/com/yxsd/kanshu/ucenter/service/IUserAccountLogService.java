package com.yxsd.kanshu.ucenter.service;

import com.yxsd.kanshu.base.service.IBaseService;
import com.yxsd.kanshu.ucenter.model.UserAccountLog;

/**
 * Created by hushengmeng on 2017/7/4.
 */
public interface IUserAccountLogService extends IBaseService<UserAccountLog,Long> {

    /**
     * 根据渠道号和日期统计收入
     * @param channel
     * @param day
     * @return
     */
    public Integer statisChannelMoney(Integer channel,String day);
}
