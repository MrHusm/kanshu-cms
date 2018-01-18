package com.yxsd.kanshu.ucenter.dao;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.ucenter.model.UserAccountLog;

import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
public interface IUserAccountLogDao extends IBaseDao<UserAccountLog> {

    Map<String, Object> statisChannelMoney(Integer channel, String day);

    List<Map<String,Object>> statisChannelBookMoney(Map<String, Object> condition);
}
