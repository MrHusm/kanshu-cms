package com.yxsd.kanshu.portal.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.portal.dao.IDriveBookCycleDao;
import com.yxsd.kanshu.portal.model.DriveBookCycle;
import com.yxsd.kanshu.portal.service.IDriveBookCycleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="driveBookCycleService")
public class DriveBookCycleServiceImpl extends BaseServiceImpl<DriveBookCycle, Long> implements IDriveBookCycleService{

    @Resource(name="driveBookCycleDao")
    private IDriveBookCycleDao driveBookCycleDao;

    @Override
    public IBaseDao<DriveBookCycle> getBaseDao() {
        return driveBookCycleDao;
    }
}
