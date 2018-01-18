package com.yxsd.kanshu.ucenter.service;

import com.yxsd.kanshu.base.service.IBaseService;
import com.yxsd.kanshu.ucenter.model.UserAccountLog;

import java.util.List;
import java.util.Map;

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
    public Map<String, Object> statisChannelMoney(Integer channel,String day);

    /**
     * 根据条件统计图书收入数据
     * @param condition
     * @return
     */
    public List<Map<String,Object>> statisChannelBookMoney(Map<String,Object> condition);
}
