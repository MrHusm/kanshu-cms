package com.yxsd.kanshu.product.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.product.dao.IChannelDataDao;
import com.yxsd.kanshu.product.model.ChannelData;
import org.springframework.stereotype.Repository;

/**
 * Created by hushengmeng on 2017/7/4.
 */
@Repository(value="channelDataDao")
public class ChannelDataDaoImpl extends BaseDaoImpl<ChannelData> implements IChannelDataDao {
}
