package com.yxsd.kanshu.ucenter.dao;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.ucenter.model.UserAccountLog;

/**
 * Created by hushengmeng on 2017/7/4.
 */
public interface IUserAccountLogDao extends IBaseDao<UserAccountLog> {

    public Integer statisChannelMoney(Integer channel, String day);
}
