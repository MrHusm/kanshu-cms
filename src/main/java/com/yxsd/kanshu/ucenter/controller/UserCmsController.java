package com.yxsd.kanshu.ucenter.controller;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.PageFinder;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.ucenter.model.UserCms;
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
import java.util.Date;

/**
 * @author hushengmeng
 * @date 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("userCms")
public class UserCmsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserCmsController.class);

    @Resource(name="userCmsService")
    IUserCmsService userCmsService;

    @RequestMapping("list")
    public String list(HttpServletResponse response,HttpServletRequest request,UserCms condition,Model model){
        String page = request.getParameter("page");

        Query query = new Query();
        if(StringUtils.isNotBlank(page)){
            query.setPage(Integer.parseInt(page));
        }else{
            query.setPage(1);
        }
        query.setPageSize(10);
        PageFinder<UserCms> pageFinder = userCmsService.findPageFinderObjs(condition,query);

        if(pageFinder!=null){
            model.addAttribute("pageFinder",pageFinder);
        }
        model.addAttribute("condition",condition);
        return "ucenter/usercms_list";
    }

    @RequestMapping("delete")
    public String delete(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        this.userCmsService.deleteById(Long.parseLong(id));
        return "redirect:/userCms/list.go";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        UserCms userCms = this.userCmsService.findMasterById(Long.parseLong(id));
        model.addAttribute("userCms",userCms);
        return "/ucenter/usercms_update";
    }

    @RequestMapping("update")
    public String update(HttpServletResponse response,HttpServletRequest request,Model model,UserCms userCms){
        userCms.setUpdateDate(new Date());
        this.userCmsService.update(userCms);
        return "redirect:/userCms/list.go";
    }

    @RequestMapping("toAdd")
    public String toAdd(HttpServletResponse response,HttpServletRequest request,Model model){
        return "/ucenter/usercms_add";
    }

    @RequestMapping("add")
    public String add(HttpServletResponse response,HttpServletRequest request,Model model,UserCms userCms){
        userCms.setCreateDate(new Date());
        userCms.setUpdateDate(new Date());
        this.userCmsService.save(userCms);
        return "redirect:/userCms/list.go";
    }
}
