package com.yxsd.kanshu.ucenter.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.ucenter.dao.IUserAccountLogDao;
import com.yxsd.kanshu.ucenter.model.UserAccountLog;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="userAccountLogDao")
public class UserAccountLogDaoImpl extends BaseDaoImpl<UserAccountLog> implements IUserAccountLogDao {

    @Override
    public Integer statisChannelMoney(Integer channel, String day) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("channel",channel);
        param.put("day",day);
        return (Integer) this.getSqlSessionQueryTemplate().selectOne("UserAccountLogMapper.statisChannelMoney",param);
    }

    @Override
    public List<Map<String, Object>> statisChannelBookMoney(Map<String, Object> condition) {
        return (List<Map<String, Object>>) this.getSqlSessionQueryTemplate().selectList("UserAccountLogMapper.statisChannelBookMoney",condition);
    }
}
