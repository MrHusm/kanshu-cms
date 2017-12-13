package com.yxsd.kanshu.ucenter.controller;

import com.yxsd.kanshu.base.contants.Constants;
import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.AppUtil;
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

/**
 * @author hushengmeng
 * @date 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("login")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource(name="userCmsService")
    IUserCmsService userCmsService;

    @RequestMapping("index")
    public String login(UserCms usercms) {
        return "ucenter/login";
    }

    /**
     * 登录
     * @param response
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletResponse response,HttpServletRequest request,Model model,UserCms userCms){
        boolean loginFlag = false;
        UserCms cmsUserInDB = null;

        //如果用户已经登录，那么直接到主页面
//        UserCms currentUser = (UserCms)AppUtil.getSession().getAttribute(Constants.CMS_USER_INFO_STORED_IN_SESSION);
//        if(currentUser!=null){
//            return "main";
//        }
        try{
            if(StringUtils.isEmpty(userCms.getLoginName()) || StringUtils.isEmpty(userCms.getPassword())){
                model.addAttribute("errorMessage","登陆失败，登录名或密码不能为空");
            }else{
                cmsUserInDB = userCmsService.findMasterUniqueByParams("loginName",userCms.getLoginName());
                if(cmsUserInDB == null){
                    model.addAttribute("errorMessage","登陆失败，该用户不存在");
                }else if(!userCms.getPassword().equals(cmsUserInDB.getPassword())){
                    model.addAttribute("errorMessage","登陆失败，密码不正确");
                }else{
                    loginFlag = true;
                    AppUtil.getSession().setAttribute(Constants.CMS_USER_INFO_STORED_IN_SESSION, cmsUserInDB);
                    model.addAttribute("userCms",cmsUserInDB);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","登陆失败，系统内部错误，请联系管理员");
        }
        if(!loginFlag){
            model.addAttribute("userCms",userCms);
            return "ucenter/login";
        }else{
            return "redirect:/channelData/list.go";
        }
    }
}
