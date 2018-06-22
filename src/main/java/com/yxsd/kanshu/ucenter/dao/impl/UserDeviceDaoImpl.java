package com.yxsd.kanshu.ucenter.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.ucenter.dao.IUserDeviceDao;
import com.yxsd.kanshu.ucenter.model.UserDevice;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="userDeviceDao")
public class UserDeviceDaoImpl extends BaseDaoImpl<UserDevice> implements IUserDeviceDao {
    @Override
    public List<UserDevice> getNotQiandaoUserDevice(Integer type) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("type",type);
        return (List<UserDevice>) this.getSqlSessionQueryTemplate().selectList("UserDeviceMapper.getNotQiandaoUserDevice",param);
    }
}
