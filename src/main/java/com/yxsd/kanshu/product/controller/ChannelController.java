package com.yxsd.kanshu.product.controller;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.PageFinder;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.product.model.Channel;
import com.yxsd.kanshu.product.service.IChannelService;
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
import java.util.Date;

/**
 * @author hushengmeng
 * @date 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("channel")
public class ChannelController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);

    @Resource(name="channelService")
    IChannelService channelService;

    @RequestMapping("list")
    public String list(HttpServletResponse response,HttpServletRequest request,Channel condition,Model model){
        String page = request.getParameter("page");
        Query query = new Query();
        if(StringUtils.isNotBlank(page)){
            query.setPage(Integer.parseInt(page));
        }else{
            query.setPage(1);
        }
        query.setPageSize(10);

        PageFinder<Channel> pageFinder = channelService.findPageFinderObjs(condition,query);
        if(pageFinder!=null){
            model.addAttribute("pageFinder",pageFinder);
        }
        model.addAttribute("condition",condition);
        return "/product/channel_list";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        Channel channel = this.channelService.findMasterById(Long.parseLong(id));
        model.addAttribute("channel",channel);
        return "/product/channel_update";
    }

    @RequestMapping("update")
    public String update(HttpServletResponse response,HttpServletRequest request,Model model,Channel channel){
        channel.setUpdateDate(new Date());
        this.channelService.update(channel);
        return "redirect:/channel/list.go";
    }

    @RequestMapping("delete")
    public String delete(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        this.channelService.deleteById(Long.parseLong(id));
        return "redirect:/channel/list.go";
    }

    @RequestMapping("toAdd")
    public String toAdd(HttpServletResponse response,HttpServletRequest request,Model model){
        return "/product/channel_add";
    }

    @RequestMapping("add")
    public String add(HttpServletResponse response,HttpServletRequest request,Model model,Channel channel){
        channel.setCreateDate(new Date());
        channel.setUpdateDate(new Date());
        this.channelService.save(channel);
        return "redirect:/channel/list.go";
    }
}
