package com.yxsd.kanshu.product.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.product.dao.IChannelDao;
import com.yxsd.kanshu.product.model.Channel;
import com.yxsd.kanshu.product.service.IChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Service(value="channelService")
public class ChannelServiceImpl extends BaseServiceImpl<Channel, Long> implements IChannelService {

    @Resource(name="channelDao")
    private IChannelDao channelDao;

    @Override
    public IBaseDao<Channel> getBaseDao() {
        return channelDao;
    }

}
