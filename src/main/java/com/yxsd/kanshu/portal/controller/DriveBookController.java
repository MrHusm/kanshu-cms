package com.yxsd.kanshu.portal.controller;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.PageFinder;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.portal.model.DriveBook;
import com.yxsd.kanshu.portal.model.DriveBookCycle;
import com.yxsd.kanshu.portal.model.DriveType;
import com.yxsd.kanshu.portal.service.IDriveBookService;
import com.yxsd.kanshu.portal.service.IDriveTypeService;
import com.yxsd.kanshu.product.model.Book;
import com.yxsd.kanshu.product.service.IBookService;
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
import java.util.List;

/**
 * Created by lenovo on 2017/12/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("driveBook")
public class DriveBookController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DriveBookController.class);

    @Resource(name="driveTypeService")
    IDriveTypeService driveTypeService;

    @Resource(name="driveBookService")
    IDriveBookService driveBookService;

    @Resource(name="bookService")
    IBookService bookService;

    @RequestMapping("list")
    public String list(HttpServletResponse response, HttpServletRequest request, DriveBookCycle condition, Model model){
        String page = request.getParameter("page");
        Query query = new Query();
        if(StringUtils.isNotBlank(page)){
            query.setPage(Integer.parseInt(page));
        }else{
            query.setPage(1);
        }
        query.setPageSize(50);

        PageFinder<DriveBook> pageFinder = driveBookService.findPageFinderObjs(condition,query);
        if(pageFinder!=null){
            model.addAttribute("pageFinder",pageFinder);
        }
        model.addAttribute("condition",condition);
        List<DriveType> driveTypes = this.driveTypeService.findListByParamsObjs(null);
        model.addAttribute("driveTypes",driveTypes);
        return "/portal/driveBook_list";
    }

    @RequestMapping("delete")
    public String delete(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        this.driveBookService.deleteById(Long.parseLong(id));
        return "redirect:/driveBook/list.go";
    }

    @RequestMapping("batchDelete")
    public String batchDelete(HttpServletResponse response,HttpServletRequest request,Model model){
        String ids = request.getParameter("ids");
        for(String id : ids.split(",")){
            if(StringUtils.isNotBlank(id)){
                this.driveBookService.deleteById(Long.parseLong(id));
            }
        }
        return "redirect:/driveBook/list.go";
    }

    @RequestMapping("toAdd")
    public String toAdd(HttpServletResponse response,HttpServletRequest request,Model model){
        List<DriveType> driveTypes = this.driveTypeService.findListByParamsObjs(null);
        model.addAttribute("driveTypes",driveTypes);
        return "/portal/driveBook_add";
    }

    @RequestMapping("add")
    public String add(HttpServletResponse response,HttpServletRequest request,Model model){
        try{
            String bookIds = request.getParameter("bookIds");
            String type = request.getParameter("type");
            String num = request.getParameter("num");
            for(int i = 0; i < bookIds.split(",").length; i++){
                String bookId = bookIds.split(",")[i];
                Book book = this.bookService.get(Long.parseLong(bookId));
                if(book == null){
                    continue;
                }
                DriveBook driveBook = this.driveBookService.findUniqueByParams("bookId",bookId,"type",type,"status",1);
                if(driveBook == null){
                    driveBook = new DriveBook();
                    driveBook.setManType(1);
                    driveBook.setStatus(1);
                    driveBook.setScore(bookIds.split(",").length - i);
                    driveBook.setType(Integer.parseInt(type));
                    driveBook.setBookId(Long.parseLong(bookId));
                    if(StringUtils.isNotBlank(num)){
                        driveBook.setNum(Integer.parseInt(num));
                    }
                    driveBook.setCreateDate(new Date());
                    driveBook.setUpdateDate(new Date());
                    this.driveBookService.save(driveBook);
                }else{
                    driveBook.setManType(1);
                    driveBook.setScore(bookIds.split(",").length - i);
                    driveBook.setStatus(1);
                    if(StringUtils.isNotBlank(num)){
                        driveBook.setNum(Integer.parseInt(num));
                    }
                    driveBook.setUpdateDate(new Date());
                    this.driveBookService.update(driveBook);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/driveBook/list.go";
    }

    @RequestMapping("updateScore")
    public String updateScore(HttpServletResponse response,HttpServletRequest request,Model model,DriveBook driveBook){
        driveBook.setUpdateDate(new Date());
        this.driveBookService.update(driveBook);
        return "redirect:/driveBook/list.go";
    }

}
