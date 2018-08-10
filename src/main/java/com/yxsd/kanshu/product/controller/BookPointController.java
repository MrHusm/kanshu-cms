package com.yxsd.kanshu.product.controller;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.PageFinder;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.product.model.BookChannelPoint;
import com.yxsd.kanshu.product.model.BookPoint;
import com.yxsd.kanshu.product.service.IBookChannelPointService;
import com.yxsd.kanshu.product.service.IBookPointService;
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
 * @author hushengmeng
 * @date 2017/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("bookPoint")
public class BookPointController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BookPointController.class);

    @Resource(name="bookPointService")
    IBookPointService bookPointService;

    @Resource(name="bookChannelPointService")
    IBookChannelPointService bookChannelPointService;

    @RequestMapping("list")
    public String list(HttpServletResponse response,HttpServletRequest request,BookPoint condition,Model model){
        String page = request.getParameter("page");
        Query query = new Query();
        if(StringUtils.isNotBlank(page)){
            query.setPage(Integer.parseInt(page));
        }else{
            query.setPage(1);
        }
        query.setPageSize(50);

        PageFinder<BookPoint> pageFinder = bookPointService.findPageFinderObjs(condition,query);
        if(pageFinder!=null){
            model.addAttribute("pageFinder",pageFinder);
        }
        model.addAttribute("condition",condition);
        return "/product/bookPoint_list";
    }


    @RequestMapping("updatePoint")
    public String updatePoint(HttpServletResponse response,HttpServletRequest request,Model model,BookPoint bookPoint){
        bookPoint.setUpdateDate(new Date());
        this.bookPointService.update(bookPoint);
        return "redirect:/bookPoint/list.go";
    }

    @RequestMapping("toUpdateChannel")
    public String toUpdateChannel(HttpServletResponse response,HttpServletRequest request,Model model){
        List<BookChannelPoint> bookChannelPoints =  bookChannelPointService.findListByParamsObjs(null);
        model.addAttribute("bookChannelPoint",bookChannelPoints.get(0));
        return "/product/bookChannelPoint_update";
    }

    @RequestMapping("updateChannel")
    public String updateChannel(HttpServletResponse response,HttpServletRequest request,Model model){
        String channels = request.getParameter("channels");
        List<BookChannelPoint> bookChannelPoints =  bookChannelPointService.findListByParamsObjs(null);
        BookChannelPoint bookChannelPoint = bookChannelPoints.get(0);
        bookChannelPoint.setChannels(channels);
        bookChannelPoint.setUpdateDate(new Date());
        bookChannelPointService.update(bookChannelPoint);
        return "redirect:/bookPoint/list.go";
    }

    @RequestMapping("delete")
    public String delete(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        this.bookPointService.deleteById(Long.parseLong(id));
        return "redirect:/bookPoint/list.go";
    }

    @RequestMapping("toAdd")
    public String toAdd(HttpServletResponse response,HttpServletRequest request,Model model){
        return "/product/bookPoint_add";
    }

    @RequestMapping("add")
    public String add(HttpServletResponse response,HttpServletRequest request,Model model,BookPoint bookPoint){
        bookPoint.setCreateDate(new Date());
        bookPoint.setUpdateDate(new Date());
        this.bookPointService.save(bookPoint);
        return "redirect:/bookPoint/list.go";
    }
}
