package com.yxsd.kanshu.ucenter.service;

import com.yxsd.kanshu.base.service.IBaseService;
import com.yxsd.kanshu.ucenter.model.UserAccessLog;

import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
public interface IUserAccessLogService extends IBaseService<UserAccessLog,Long> {

    /**
     * 根据日期统计渠道UV
     * @param day
     * @return
     */
    public List<Map<String,Object>> statisChannelUv(String day);
}
