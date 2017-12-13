package com.yxsd.kanshu.product.controller;

import com.yxsd.kanshu.base.contants.Constants;
import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.AppUtil;
import com.yxsd.kanshu.base.utils.PageFinder;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.product.model.Channel;
import com.yxsd.kanshu.product.model.ChannelData;
import com.yxsd.kanshu.product.service.IChannelDataService;
import com.yxsd.kanshu.product.service.IChannelService;
import com.yxsd.kanshu.ucenter.model.UserCms;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @author hushengmeng
 * @date 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("channelData")
public class ChannelDataController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ChannelDataController.class);

    @Resource(name="channelDataService")
    IChannelDataService channelDataService;

    @Resource(name="channelService")
    IChannelService channelService;

    @RequestMapping("index")
    public String login(UserCms usercms) {
        return "ucenter/login";
    }

    @RequestMapping("list")
    public String list(HttpServletResponse response,HttpServletRequest request,ChannelData condition,Model model){
        String page = request.getParameter("page");
        Query query = new Query();
        if(StringUtils.isNotBlank(page)){
            query.setPage(Integer.parseInt(page));
        }else{
            query.setPage(1);
        }
        query.setPageSize(10);
        UserCms currentUser = (UserCms) AppUtil.getSession().getAttribute(Constants.CMS_USER_INFO_STORED_IN_SESSION);
        if(currentUser.getAdminFlag() == 0){
            if(StringUtils.isBlank(currentUser.getChannels())){
                return "ucenter/login";
            }else{
                condition.setChannels(currentUser.getChannels());
                condition.setStatus(1);
            }
        }

        PageFinder<ChannelData> pageFinder = channelDataService.findPageFinderObjs(condition,query);
        if(pageFinder!=null){
            model.addAttribute("pageFinder",pageFinder);
        }
        model.addAttribute("condition",condition);
        if(currentUser.getAdminFlag() == 0){
            return "/product/channelData_list";
        }else{
            return "/product/channelData_admin_list";
        }
    }

    @RequestMapping("toUpdate")
    public String toUpdate(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        ChannelData channelData = this.channelDataService.findMasterById(Long.parseLong(id));
        model.addAttribute("channelData",channelData);
        return "/product/channelData_update";
    }

    @RequestMapping("update")
    public String update(HttpServletResponse response,HttpServletRequest request,Model model,ChannelData channelData){
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
            channelData.setMoneyFixed(channelData.getMoneyFixed());
        }else if(channel.getMoneyRatio() != null && channel.getMoneyRatio() != 0){
            channelData.setMoneyShow((int)(channelData.getMoney() * channel.getMoneyRatio()));
        }
        this.channelDataService.update(channelData);
        return "redirect:/channelData/list.go";
    }

    /**
     * 数据发布
     * @param response
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("publish")
    public String publish(HttpServletResponse response,HttpServletRequest request,Model model){
        String ids = request.getParameter("ids");
        if(ids != null){
            for(String id : ids.split(",")){
                if(StringUtils.isNotBlank(id)){
                    ChannelData channelData = this.channelDataService.get(Long.parseLong(id));
                    channelData.setStatus(1);
                    this.channelDataService.update(channelData);
                }
            }
        }
        return "redirect:/channelData/list.go";
    }

    /**
     * 导出数据
     * @param response
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("export")
    public String export(HttpServletResponse response,HttpServletRequest request,ChannelData condition,Model model){
        Query query = new Query();
        UserCms currentUser = (UserCms) AppUtil.getSession().getAttribute(Constants.CMS_USER_INFO_STORED_IN_SESSION);
        if(currentUser.getAdminFlag() == 0){
            condition.setChannels(currentUser.getChannels());
            condition.setStatus(1);
        }
        List<ChannelData> channelDatas = this.channelDataService.findListByParamsObjs(condition);
        if(CollectionUtils.isNotEmpty(channelDatas)){
            model.addAttribute("channelDatas",channelDatas);
        }
        try {
            response.addHeader("Content-Disposition", "attachment;filename="+ new String("渠道数据.xls".getBytes("utf-8"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(currentUser.getAdminFlag() == 0){
            return "/product/channelData_export";
        }else{
            return "/product/channelData_admin_export";
        }
    }
}
