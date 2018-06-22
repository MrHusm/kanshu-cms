package com.yxsd.kanshu.ucenter.service.impl;

import com.yxsd.kanshu.base.contants.RedisKeyConstants;
import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.ucenter.dao.IUserWelfareDao;
import com.yxsd.kanshu.ucenter.model.UserWelfare;
import com.yxsd.kanshu.ucenter.service.IUserWelfareService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hushengmeng on 2018/1/9.
 */
@Service(value="userWelfareService")
public class UserWelfareServiceImpl extends BaseServiceImpl<UserWelfare, Long> implements IUserWelfareService {

    @Resource(name="userWelfareDao")
    private IUserWelfareDao userWelfareDao;

    @Resource(name = "masterRedisTemplate")
    private RedisTemplate<String,Integer> masterRedisTemplate;

    @Resource(name = "slaveRedisTemplate")
    private RedisTemplate<String,Integer> slaveRedisTemplate;

    @Override
    public IBaseDao<UserWelfare> getBaseDao() {
        return userWelfareDao;
    }

    @Override
    public Integer getUserWelfareType() {
        String key = RedisKeyConstants.CACHE_NEW_USER_WELFARE_TYPE_KEY;
        Integer type = slaveRedisTemplate.opsForValue().get(key);
        if(type == null){
            List<UserWelfare> list = this.findListByParamsObjs(null);
            if(CollectionUtils.isNotEmpty(list)){
                UserWelfare userWelfare = list.get(0);
                type = (int)userWelfare.getType();
                masterRedisTemplate.opsForValue().set(key, type, 5, TimeUnit.DAYS);
            }else{
                type = 1;
            }
        }
        return type;
    }
}
