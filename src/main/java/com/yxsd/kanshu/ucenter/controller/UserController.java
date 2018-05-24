package com.yxsd.kanshu.ucenter.controller;

import com.yxsd.kanshu.base.contants.Constants;
import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.AppUtil;
import com.yxsd.kanshu.base.utils.PageFinder;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.product.model.ChannelData;
import com.yxsd.kanshu.ucenter.model.UserAccountLog;
import com.yxsd.kanshu.ucenter.model.UserCms;
import com.yxsd.kanshu.ucenter.service.IUserAccountLogService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/5/7.
 */
@Controller
@Scope("prototype")
@RequestMapping("user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name="userAccountLogService")
    IUserAccountLogService userAccountLogService;

    @RequestMapping("list")
    public String list(HttpServletResponse response, HttpServletRequest request, Model model){
        String page = request.getParameter("page");
        Query query = new Query();
        if(StringUtils.isNotBlank(page)){
            query.setPage(Integer.parseInt(page));
        }else{
            query.setPage(1);
        }
        query.setPageSize(10);
        UserCms user = (UserCms) AppUtil.getSession().getAttribute(Constants.CMS_USER_INFO_STORED_IN_SESSION);
        Map<String,Object> condition = new HashMap<String, Object>();

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String channel = request.getParameter("channel");

        if(StringUtils.isNotBlank(startDate)){
            startDate = startDate + " 00:00:00";
            condition.put("startDate",startDate);
        }
        if(StringUtils.isNotBlank(endDate)){
            endDate = endDate + " 23:59:59";
            condition.put("endDate",endDate);
        }
        if(StringUtils.isNotBlank(channel)){
            condition.put("channel",channel);
        }
        if(user.getAdminFlag() != 1){
            condition.put("channels",user.getChannels());
        }
        condition.put("types","1,2");

        PageFinder<UserAccountLog> pageFinder = userAccountLogService.findPageFinderObjs(condition,query);
        if(pageFinder!=null){
            model.addAttribute("pageFinder",pageFinder);
        }

        model.addAttribute("condition",condition);
        return "/ucenter/userAccountLog_list";
    }
}
