package com.yxsd.kanshu.product.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.product.dao.IChannelDataDao;
import com.yxsd.kanshu.product.model.ChannelData;
import com.yxsd.kanshu.product.service.IChannelDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="channelDataService")
public class ChannelDataServiceImpl extends BaseServiceImpl<ChannelData, Long> implements IChannelDataService {

    @Resource(name="channelDataDao")
    private IChannelDataDao channelDataDao;

    @Override
    public IBaseDao<ChannelData> getBaseDao() {
        return channelDataDao;
    }

}
