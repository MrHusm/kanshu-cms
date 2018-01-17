package com.yxsd.kanshu.ucenter.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.ucenter.dao.IUserAccountLogDao;
import com.yxsd.kanshu.ucenter.model.UserAccountLog;
import com.yxsd.kanshu.ucenter.service.IUserAccountLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="userAccountLogService")
public class UserAccountLogServiceImpl extends BaseServiceImpl<UserAccountLog, Long> implements IUserAccountLogService {

    @Resource(name="userAccountLogDao")
    private IUserAccountLogDao userAccountLogDao;

    @Override
    public IBaseDao<UserAccountLog> getBaseDao() {
        return userAccountLogDao;
    }


    @Override
    public Integer statisChannelMoney(Integer channel, String day) {
        return this.userAccountLogDao.statisChannelMoney(channel,day);
    }

    @Override
    public List<Map<String, Object>> statisChannelBookMoney(Map<String, Object> condition) {
        return this.userAccountLogDao.statisChannelBookMoney(condition);
    }
}
