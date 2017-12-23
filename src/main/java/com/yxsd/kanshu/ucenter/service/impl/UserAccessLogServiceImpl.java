package com.yxsd.kanshu.ucenter.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.ucenter.dao.IUserAccessLogDao;
import com.yxsd.kanshu.ucenter.model.UserAccessLog;
import com.yxsd.kanshu.ucenter.service.IUserAccessLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="userAccessLogService")
public class UserAccessLogServiceImpl extends BaseServiceImpl<UserAccessLog, Long> implements IUserAccessLogService {

    @Resource(name="userAccessLogDao")
    private IUserAccessLogDao userAccessLogDao;

    @Override
    public IBaseDao<UserAccessLog> getBaseDao() {
        return userAccessLogDao;
    }

    @Override
    public List<Map<String,Object>> statisChannelUv(String day) {
        return userAccessLogDao.statisChannelUv(day);
    }
}
