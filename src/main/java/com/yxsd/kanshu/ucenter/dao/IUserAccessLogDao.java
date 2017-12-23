package com.yxsd.kanshu.ucenter.dao;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.ucenter.model.UserAccessLog;

import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
public interface IUserAccessLogDao extends IBaseDao<UserAccessLog> {

    /**
     * 根据日期统计渠道UV
      * @param day
     * @return
     */
   public List<Map<String,Object>> statisChannelUv(String day);
}
