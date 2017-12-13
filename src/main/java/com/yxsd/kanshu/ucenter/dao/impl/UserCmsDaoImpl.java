package com.yxsd.kanshu.ucenter.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.ucenter.dao.IUserCmsDao;
import com.yxsd.kanshu.ucenter.model.UserCms;
import org.springframework.stereotype.Repository;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="userCmsDao")
public class UserCmsDaoImpl extends BaseDaoImpl<UserCms> implements IUserCmsDao {
}
