package com.yxsd.kanshu.portal.controller;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.portal.model.DriveType;
import com.yxsd.kanshu.portal.service.IDriveBookService;
import com.yxsd.kanshu.portal.service.IDriveTypeService;
import com.yxsd.kanshu.product.service.IBookService;
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
import java.util.List;

/**
 * Created by lenovo on 2017/12/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("driveType")
public class DriveTypeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DriveTypeController.class);

    @Resource(name="driveTypeService")
    IDriveTypeService driveTypeService;

    @Resource(name="driveBookService")
    IDriveBookService driveBookService;

    @Resource(name="bookService")
    IBookService bookService;

    @RequestMapping("list")
    public String list(HttpServletResponse response, HttpServletRequest request, Model model){
        List<DriveType> driveTypes = this.driveTypeService.findListByParamsObjs(null);
        model.addAttribute("data",driveTypes);
        return "/portal/driveType_list";
    }

    @RequestMapping("delete")
    public String batchDelete(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        DriveType driveType = this.driveTypeService.get(Long.parseLong(id));
        this.driveBookService.deleteByByParams("type",driveType.getType());
        this.driveTypeService.deleteById(Long.parseLong(id));
        return "redirect:/driveType/list.go";
    }

    @RequestMapping("toAdd")
    public String toAdd(HttpServletResponse response,HttpServletRequest request,Model model){
        return "/portal/driveType_add";
    }

    @RequestMapping("add")
    public String add(HttpServletResponse response,HttpServletRequest request,Model model,DriveType driveType){
        try{
            driveType.setCreateDate(new Date());
            driveType.setUpdateDate(new Date());
            this.driveTypeService.save(driveType);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/driveType/list.go";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        DriveType driveType = this.driveTypeService.findMasterById(Long.parseLong(id));
        model.addAttribute("driveType",driveType);
        return "/portal/driveType_update";
    }

    @RequestMapping("update")
    public String update(HttpServletResponse response,HttpServletRequest request,Model model,DriveType driveType){
        driveType.setUpdateDate(new Date());
        this.driveTypeService.update(driveType);
        return "redirect:/driveType/list.go";
    }
}
