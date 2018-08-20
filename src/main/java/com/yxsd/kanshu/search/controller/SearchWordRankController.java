package com.yxsd.kanshu.search.controller;

import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.base.utils.Query;
import com.yxsd.kanshu.search.model.SearchWordRank;
import com.yxsd.kanshu.search.service.ISearchWordRankService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/8/20.
 */
@Controller
@Scope("prototype")
@RequestMapping("searchWordRank")
public class SearchWordRankController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SearchWordRankController.class);

    @Resource(name="searchWordRankService")
    ISearchWordRankService searchWordRankService;

    @RequestMapping("list")
    public String list(HttpServletResponse response, HttpServletRequest request, SearchWordRank condition, Model model){
        String page = request.getParameter("page");
        Query query = new Query();
        if(StringUtils.isNotBlank(page)){
            query.setPage(Integer.parseInt(page));
        }else{
            query.setPage(1);
        }
        query.setPageSize(50);

        List<SearchWordRank> searchWordRanks = this.searchWordRankService.findListByParams("word",condition.getWord());;

        if(CollectionUtils.isNotEmpty(searchWordRanks)){
            model.addAttribute("searchWordRanks",searchWordRanks);
        }
        model.addAttribute("condition",condition);
        return "/search/searchWordRank_list";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(HttpServletResponse response,HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        SearchWordRank SearchWordRank = this.searchWordRankService.findMasterById(Long.parseLong(id));
        model.addAttribute("searchWordRank",SearchWordRank);
        return "/search/SearchWordRank_update";
    }

    @RequestMapping("update")
    public String update(HttpServletResponse response,HttpServletRequest request,Model model,SearchWordRank SearchWordRank){
        SearchWordRank.setUpdateDate(new Date());
        this.searchWordRankService.update(SearchWordRank);
        return "redirect:/searchWordRank/list.go";
    }

    @RequestMapping("delete")
    public String delete(HttpServletResponse response, HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        this.searchWordRankService.deleteById(Long.parseLong(id));
        return "redirect:/searchWordRank/list.go";
    }

    @RequestMapping("toAdd")
    public String toAdd(HttpServletResponse response,HttpServletRequest request,Model model){
        return "/search/searchWordRank_add";
    }

    @RequestMapping("add")
    public String add(HttpServletResponse response,HttpServletRequest request,Model model,SearchWordRank SearchWordRank){
        SearchWordRank.setCreateDate(new Date());
        SearchWordRank.setUpdateDate(new Date());
        this.searchWordRankService.save(SearchWordRank);
        return "redirect:/searchWordRank/list.go";
    }
}
