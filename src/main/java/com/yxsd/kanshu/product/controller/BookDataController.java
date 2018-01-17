package com.yxsd.kanshu.product.controller;

import com.yxsd.kanshu.base.contants.Constants;
import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.AppUtil;
import com.yxsd.kanshu.ucenter.model.UserCms;
import com.yxsd.kanshu.ucenter.service.IUserAccountLogService;
import com.yxsd.kanshu.ucenter.service.IUserCmsService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hushengmeng
 * @date 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("bookData")
public class BookDataController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BookDataController.class);

    @Resource(name="userAccountLogService")
    IUserAccountLogService userAccountLogService;

    @Resource(name="userCmsService")
    IUserCmsService userCmsService;


    @RequestMapping("list")
    public String list(HttpServletResponse response, HttpServletRequest request, Model model){
        String type = request.getParameter("type");
        UserCms user = (UserCms) AppUtil.getSession().getAttribute(Constants.CMS_USER_INFO_STORED_IN_SESSION);
        Map<String,Object> condition = new HashMap<String, Object>();
        if(StringUtils.isBlank(type)){
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String title = request.getParameter("title");
            String channel = request.getParameter("channel");

            if(StringUtils.isNotBlank(startDate)){
                startDate = startDate + " 00:00:00";
                condition.put("startDate",startDate);
            }
            if(StringUtils.isNotBlank(endDate)){
                endDate = endDate + " 23:59:59";
                condition.put("endDate",endDate);
            }
            if(StringUtils.isNotBlank(title)){
                condition.put("title",title);
            }
            if(StringUtils.isNotBlank(channel)){
                condition.put("channel",channel);
            }
            if(user.getAdminFlag() != 1){
                condition.put("channels",user.getChannels());
            }

            List<Map<String,Object>> list = this.userAccountLogService.statisChannelBookMoney(condition);
            model.addAttribute("data",list);
        }
        model.addAttribute("condition",condition);
        return "/product/bookData_list";
    }


    /**
     * 导出数据
     * @param response
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("export")
    public String export(HttpServletResponse response,HttpServletRequest request,Model model){
        UserCms user = (UserCms) AppUtil.getSession().getAttribute(Constants.CMS_USER_INFO_STORED_IN_SESSION);
        Map<String,Object> condition = new HashMap<String, Object>();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String title = request.getParameter("title");
        String channel = request.getParameter("channel");

        if(StringUtils.isNotBlank(startDate)){
            startDate = startDate + " 00:00:00";
            condition.put("startDate",startDate);
        }
        if(StringUtils.isNotBlank(endDate)){
            endDate = endDate + " 23:59:59";
            condition.put("endDate",endDate);
        }
        if(StringUtils.isNotBlank(title)){
            condition.put("title",title);
        }
        if(StringUtils.isNotBlank(channel)){
            condition.put("channel",channel);
        }
        if(user.getAdminFlag() != 1){
            condition.put("channels",user.getChannels());
        }

        List<Map<String,Object>> list = this.userAccountLogService.statisChannelBookMoney(condition);
        model.addAttribute("data",list);

        try {
            response.addHeader("Content-Disposition", "attachment;filename="+ new String("图书数据.xls".getBytes("utf-8"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "/product/bookData_list_export";
    }
}
