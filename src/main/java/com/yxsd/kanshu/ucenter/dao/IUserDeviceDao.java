package com.yxsd.kanshu.ucenter.dao;


import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.ucenter.model.UserDevice;

import java.util.List;

/**
 * Created by hushengmeng on 2017/7/4.
 */
public interface IUserDeviceDao extends IBaseDao<UserDevice> {

    /**
     * 获取新用户福利类型
     * @return 1：领VIP 2：签到
     */
    List<UserDevice> getNotQiandaoUserDevice(Integer type);
}
