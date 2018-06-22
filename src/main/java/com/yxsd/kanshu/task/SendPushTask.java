package com.yxsd.kanshu.task;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.JpushClientUtil;
import com.yxsd.kanshu.ucenter.model.UserDevice;
import com.yxsd.kanshu.ucenter.service.IUserDeviceService;
import com.yxsd.kanshu.ucenter.service.IUserWelfareService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 发送push任务
 */
@Controller
@Scope("prototype")
@RequestMapping("sendPushTask")
public class SendPushTask extends BaseController {

    @Resource(name="userWelfareService")
    IUserWelfareService userWelfareService;

    @Resource(name="userDeviceService")
    IUserDeviceService userDeviceService;

    private static ThreadPoolExecutor pushPool;
    private static final int COREPOOL_SIZE = 10;
    private static final int THREAD_POOL_KEEP_ALIVE_TIME = 300;
    private static final int THREAD_POOL_QUEUE_SIZE = 200;

    /**
     * 每天20点发送当天未签到的push提醒
     */
    public void sendQiandaoPush(){
        Integer type = this.userWelfareService.getUserWelfareType();
        if(type == 2){
            List<UserDevice> userDevices = this.userDeviceService.getNotQiandaoUserDevice(1);
            if(CollectionUtils.isNotEmpty(userDevices)){
                if(pushPool == null){
                    //创建线程池
                    pushPool = new ThreadPoolExecutor(COREPOOL_SIZE, COREPOOL_SIZE, THREAD_POOL_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnable>(1000), new CallerRunsPolicy());
                }
                for(final UserDevice userDevice : userDevices){
                    pushPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            JpushClientUtil.sendToRegistrationId(userDevice.getRegistrationId(),"中奖啦","500w","快来签到领取福利啦","aa");
                        }
                    });
                }
            }
        }
    }
}
