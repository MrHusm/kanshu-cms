package com.yxsd.kanshu.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.HttpUtils;
import com.yxsd.kanshu.product.model.Channel;
import com.yxsd.kanshu.product.model.ChannelData;
import com.yxsd.kanshu.product.service.IChannelDataService;
import com.yxsd.kanshu.product.service.IChannelService;
import com.yxsd.kanshu.ucenter.service.IUserAccessLogService;
import com.yxsd.kanshu.ucenter.service.IUserAccountLogService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/6.
 */
@Controller
@Scope("prototype")
@RequestMapping("channelDataTask")
public class ChannelDataTask extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ChannelDataTask.class);

    public static final String APPKEY = "59e45e1d75ca3568d8000036";

    public static final String APPKEY_KXSSQ = "5ac075baf43e484ac700003f";

    public static final String AUTH_TOKEN = "58lOeVDCVPR52xExU32U";

    @Resource(name="channelService")
    IChannelService channelService;

    @Resource(name="channelDataService")
    IChannelDataService channelDataService;

    @Resource(name="userAccountLogService")
    IUserAccountLogService userAccountLogService;

    @Resource(name="userAccessLogService")
    IUserAccessLogService userAccessLogService;

    /**
     * 获取渠道数据 每天早晨7点执行
     */
    public void runChannelData(){
        logger.info("开始跑渠道数据");
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String day = formatter.format(new Date(currentTime.getTime() - 1 * 24 * 60 * 60 * 1000));
        //app渠道数据
        String[] appKeys = {APPKEY,APPKEY_KXSSQ};
        for(String appKey : appKeys){
            String channelsUrl = "http://api.umeng.com/channels?appkey=%s&auth_token=%s&date=yesterday&page=1&per_page=200";
            String channelsJson = HttpUtils.getContent(String.format(channelsUrl,appKey,AUTH_TOKEN),"UTF-8");
            logger.info("channelsJson:"+channelsJson);
            List<Map> channels =  JSON.parseArray(channelsJson,Map.class);
            for(Map map : channels){
                Channel channel = this.channelService.findUniqueByParams("channelName",map.get("channel").toString(),"type",1);
                if(channel == null){
                    continue;
                }
                ChannelData channelData = this.channelDataService.findUniqueByParams("day",day,"channel",channel.getChannel());
                if(channelData != null){
                    continue;
                }
                //保存渠道数据
                channelData = new ChannelData();
                channelData.setDau(Integer.parseInt(map.get("active_user").toString()));
                channelData.setDauShow(channelData.getDau());
                channelData.setDnu(Integer.parseInt(map.get("install").toString()));
                channelData.setDnuShow(channelData.getDnu());
                channelData.setDay(day);
                channelData.setChannel(channel.getChannel());
                Map<String, Object> moneyMap = this.userAccountLogService.statisChannelMoney(channel.getChannel(),day);
                if(moneyMap != null && moneyMap.get("money") != null){
                    channelData.setMoney((int)Double.parseDouble(moneyMap.get("money").toString()));
                }else{
                    channelData.setMoney(0);
                }
                if(moneyMap != null && moneyMap.get("chargeNum") != null){
                    channelData.setChargeNum((int)Double.parseDouble(moneyMap.get("chargeNum").toString()));
                }else{
                    channelData.setChargeNum(0);
                }
                channelData.setMoneyShow(channelData.getMoney());
                channelData.setStatus(0);
                channelData.setCreateDate(new Date());
                channelData.setUpdateDate(new Date());
                this.channelDataService.save(channelData);

                //保存前天次日留存数据
                String dayBefore = formatter.format(new Date(currentTime.getTime() - 2 * 24 * 60 * 60 * 1000));
                ChannelData channelDataBefore = this.channelDataService.findUniqueByParams("day",dayBefore,"channel",channel.getChannel());
                if(channelDataBefore != null){
                    String retentionsUrl = "http://api.umeng.com/retentions?appkey=%s&auth_token=%s&period_type=daily&start_date=%s&end_date=%s&channels=%s";
                    String retentionsJson = HttpUtils.getContent(String.format(retentionsUrl,appKey,AUTH_TOKEN,dayBefore,dayBefore,map.get("id").toString()),"UTF-8");
                    logger.info("retentionsJson:" + retentionsJson);
                    List<Map> retentions =  JSON.parseArray(retentionsJson,Map.class);
                    for(Map retention : retentions){
                        JSONArray retentionRate = (JSONArray)retention.get("retention_rate");
                        channelDataBefore.setOneDayRetention(retentionRate.get(0).toString() + "%");
                        channelDataService.update(channelDataBefore);
                    }
                }
            }
        }

        //H5渠道数据
        List<Map<String,Object>> list = this.userAccessLogService.statisChannelUv(day);
        if(CollectionUtils.isNotEmpty(list)){
            for(Map<String,Object> channelUv : list){
                Integer uv = Integer.parseInt(channelUv.get("uv").toString());
                Integer channelId = Integer.parseInt(channelUv.get("channel").toString());
                Channel channel = this.channelService.findUniqueByParams("channel",channelId,"type",3);
                if(channel == null){
                    continue;
                }
                ChannelData channelData = this.channelDataService.findUniqueByParams("day",day,"channel",channel.getChannel());
                if(channelData != null){
                    continue;
                }
                //保存渠道数据
                channelData = new ChannelData();
                channelData.setDau(uv);
                channelData.setDauShow(uv);
                channelData.setDnu(0);
                channelData.setDnuShow(0);
                channelData.setDay(day);
                channelData.setChannel(channel.getChannel());
                Map<String, Object> moneyMap = this.userAccountLogService.statisChannelMoney(channel.getChannel(),day);
                if(moneyMap != null && moneyMap.get("money") != null){
                    channelData.setMoney((int)Double.parseDouble(moneyMap.get("money").toString()));
                }else{
                    channelData.setMoney(0);
                }
                if(moneyMap != null && moneyMap.get("chargeNum") != null){
                    channelData.setChargeNum((int)Double.parseDouble(moneyMap.get("chargeNum").toString()));
                }else{
                    channelData.setChargeNum(0);
                }
                channelData.setMoneyShow(channelData.getMoney());
                channelData.setStatus(0);
                channelData.setCreateDate(new Date());
                channelData.setUpdateDate(new Date());
                this.channelDataService.save(channelData);
            }
        }

        logger.info("结束跑渠道数据");
    }

    /**
     * 发布渠道数据 每天14点执行
     */
    public void publishChannelData(){
        logger.info("开始发布渠道数据");
        List<ChannelData> channelDatas = this.channelDataService.findListByParams("status",0);
        if(CollectionUtils.isNotEmpty(channelDatas)){
            for(ChannelData channelData : channelDatas){
                channelData.setUpdateDate(new Date());
                Channel channel = this.channelService.findUniqueByParams("channel",channelData.getChannel());
                if(channelData.getDnuFixed() != null && channelData.getDnuFixed() != 0){
                    channelData.setDnuShow(channelData.getDnuFixed());
                }else if(channel.getDnuRatio() != null && channel.getDnuRatio() != 0){
                    channelData.setDnuShow((int)(channelData.getDnu() * channel.getDnuRatio()));
                }
                if(channelData.getDauFixed() != null && channelData.getDauFixed() != 0){
                    channelData.setDauShow(channelData.getDauFixed());
                }else if(channel.getDauRatio() != null && channel.getDauRatio() != 0){
                    channelData.setDauShow((int)(channelData.getDau() * channel.getDauRatio()));
                }
                if(channelData.getMoneyFixed() != null && channelData.getMoneyFixed() != 0){
                    channelData.setMoneyShow(channelData.getMoneyFixed());
                }else if(channel.getMoneyRatio() != null && channel.getMoneyRatio() != 0){
                    channelData.setMoneyShow((int)(channelData.getMoney() * channel.getMoneyRatio()));
                }
                channelData.setStatus(1);
                this.channelDataService.update(channelData);
            }
        }
        logger.info("结束发布渠道数据");
    }

    public static void main(String[] args) {
        ChannelDataTask task = new ChannelDataTask();
        task.runChannelData();

    }
}
