package com.yxsd.kanshu.ucenter.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.ucenter.dao.IUserWelfareDao;
import com.yxsd.kanshu.ucenter.model.UserWelfare;
import org.springframework.stereotype.Repository;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="userWelfareDao")
public class UserWelfareDaoImpl extends BaseDaoImpl<UserWelfare> implements IUserWelfareDao {
}
