package com.yxsd.kanshu.ucenter.service;


import com.yxsd.kanshu.base.service.IBaseService;
import com.yxsd.kanshu.ucenter.model.UserDevice;

import java.util.List;

/**
 * Created by hushengmeng on 2017/7/4.
 */
public interface IUserDeviceService extends IBaseService<UserDevice,Long> {

    /**
     * 获取当天未签到的用户设备
     * @param type 1：安卓用户设备 2：IOS用户设备
     * @return
     */
    List<UserDevice> getNotQiandaoUserDevice(Integer type);
}
