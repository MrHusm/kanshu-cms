package com.yxsd.kanshu.product.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.portal.service.IDriveBookService;
import com.yxsd.kanshu.product.dao.IBookPointDao;
import com.yxsd.kanshu.product.model.BookPoint;
import com.yxsd.kanshu.product.service.IBookPointService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lenovo on 2018/1/13.
 */
@Service(value="bookPointService")
public class BookPointServiceImpl extends BaseServiceImpl<BookPoint, Long> implements IBookPointService {

    @Resource(name="bookPointDao")
    private IBookPointDao bookPointDao;

    @Resource(name="driveBookService")
    private IDriveBookService driveBookService;

    @Resource(name = "masterRedisTemplate")
    private RedisTemplate<String,Map<Long,Integer>> masterRedisTemplate;

    @Resource(name = "slaveRedisTemplate")
    private RedisTemplate<String,Map<Long,Integer>> slaveRedisTemplate;

    @Override
    public IBaseDao<BookPoint> getBaseDao() {
        return bookPointDao;
    }

}
