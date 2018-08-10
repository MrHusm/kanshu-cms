package com.yxsd.kanshu.portal.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.portal.dao.IDriveTypeDao;
import com.yxsd.kanshu.portal.model.DriveType;
import com.yxsd.kanshu.portal.service.IDriveTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="driveTypeService")
public class DriveTypeServiceImpl extends BaseServiceImpl<DriveType, Long> implements IDriveTypeService {

    @Resource(name="driveTypeDao")
    private IDriveTypeDao driveTypeDao;

    @Override
    public IBaseDao<DriveType> getBaseDao() {
        return driveTypeDao;
    }
}
