package com.yxsd.kanshu.product.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.product.dao.IChannelDao;
import com.yxsd.kanshu.product.model.Channel;
import org.springframework.stereotype.Repository;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="channelDao")
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements IChannelDao {
}
