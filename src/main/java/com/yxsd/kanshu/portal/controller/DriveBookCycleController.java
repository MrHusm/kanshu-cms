package com.yxsd.kanshu.portal.controller;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.PageFinder;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.portal.model.DriveBookCycle;
import com.yxsd.kanshu.portal.service.IDriveBookCycleService;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/12/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("driveBookCycle")
public class DriveBookCycleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DriveBookCycleController.class);

    @Resource(name="driveBookCycleService")
    IDriveBookCycleService driveBookCycleService;

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
        query.setPageSize(10);

        PageFinder<DriveBookCycle> pageFinder = driveBookCycleService.findPageFinderObjs(condition,query);
        if(pageFinder!=null){
            model.addAttribute("pageFinder",pageFinder);
        }
        model.addAttribute("condition",condition);
        return "/portal/driveBookCycle_list";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        DriveBookCycle driveBookCycle = this.driveBookCycleService.findMasterById(Long.parseLong(id));
        model.addAttribute("driveBookCycle",driveBookCycle);
        return "/portal/driveBookCycle_update";
    }

    @RequestMapping("update")
    public String update(HttpServletResponse response,HttpServletRequest request,Model model,DriveBookCycle driveBookCycle){
        driveBookCycle.setUpdateDate(new Date());
        this.driveBookCycleService.update(driveBookCycle);
        return "redirect:/driveBookCycle/list.go";
    }

    @RequestMapping("delete")
    public String delete(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        this.driveBookCycleService.deleteById(Long.parseLong(id));
        return "redirect:/driveBookCycle/list.go";
    }

    @RequestMapping("toAdd")
    public String toAdd(HttpServletResponse response,HttpServletRequest request,Model model){
        return "/portal/driveBookCycle_add";
    }

    @RequestMapping("add")
    public String add(HttpServletResponse response,HttpServletRequest request,Model model,DriveBookCycle driveBookCycle){
        try{
            String bookIds = request.getParameter("bookIds");
            String startDate = request.getParameter("startDateStr");
            String endDate = request.getParameter("endDateStr");
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
            for(int i = 0; i < bookIds.split(",").length; i++){
                String bookId = bookIds.split(",")[i];
                Book book = this.bookService.get(Long.parseLong(bookId));
                if(book == null){
                    continue;
                }
                DriveBookCycle bookCycle = new DriveBookCycle();
                bookCycle.setBookId(Long.parseLong(bookId));
                bookCycle.setBookName(book.getTitle());
                bookCycle.setScore(bookIds.split(",").length - i);
                bookCycle.setStartDate(sdf.parse(startDate));
                bookCycle.setEndDate(sdf.parse(endDate));
                bookCycle.setType(driveBookCycle.getType());
                bookCycle.setCreateDate(new Date());
                bookCycle.setUpdateDate(new Date());
                this.driveBookCycleService.save(bookCycle);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/driveBookCycle/list.go";
    }

    @RequestMapping("updateScore")
    public String updateScore(HttpServletResponse response,HttpServletRequest request,Model model,DriveBookCycle driveBookCycle){
        driveBookCycle.setUpdateDate(new Date());
        this.driveBookCycleService.update(driveBookCycle);
        return "redirect:/driveBookCycle/list.go";
    }
}
