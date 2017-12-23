package com.yxsd.kanshu.ucenter.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.ucenter.dao.IUserAccessLogDao;
import com.yxsd.kanshu.ucenter.model.UserAccessLog;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="userAccessLogDao")
public class UserAccessLogDaoImpl extends BaseDaoImpl<UserAccessLog> implements IUserAccessLogDao {

    @Override
    public List<Map<String,Object>> statisChannelUv(String day) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("day",day);
        return (List<Map<String,Object>>)this.getSqlSessionQueryTemplate().selectList("UserAccessLogMapper.statisChannelUv",param);
    }
}
