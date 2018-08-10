package com.yxsd.kanshu.portal.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.portal.dao.IDriveTypeDao;
import com.yxsd.kanshu.portal.model.DriveType;
import org.springframework.stereotype.Repository;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="driveTypeDao")
public class DriveTypeDaoImpl extends BaseDaoImpl<DriveType> implements IDriveTypeDao {}
