package com.yxsd.kanshu.ucenter.service.impl;


import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.ucenter.dao.IUserDeviceDao;
import com.yxsd.kanshu.ucenter.model.UserDevice;
import com.yxsd.kanshu.ucenter.service.IUserDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="userDeviceService")
public class UserDeviceServiceImpl extends BaseServiceImpl<UserDevice, Long> implements IUserDeviceService {

    @Resource(name="userDeviceDao")
    private IUserDeviceDao userDeviceDao;

    @Override
    public IBaseDao<UserDevice> getBaseDao() {
        return userDeviceDao;
    }

    @Override
    public List<UserDevice> getNotQiandaoUserDevice(Integer type) {
        return this.userDeviceDao.getNotQiandaoUserDevice(type);
    }
}
