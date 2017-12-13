package com.yxsd.kanshu.ucenter.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.ucenter.dao.IUserCmsDao;
import com.yxsd.kanshu.ucenter.model.UserCms;
import com.yxsd.kanshu.ucenter.service.IUserCmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="userCmsService")
public class UserCmsServiceImpl extends BaseServiceImpl<UserCms, Long> implements IUserCmsService {

    @Resource(name="userCmsDao")
    private IUserCmsDao userCmsDao;

    @Override
    public IBaseDao<UserCms> getBaseDao() {
        return userCmsDao;
    }

}
