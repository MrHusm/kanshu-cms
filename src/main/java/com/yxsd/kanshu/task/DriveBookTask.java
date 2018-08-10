package com.yxsd.kanshu.task;

import com.yxsd.kanshu.base.contants.RedisKeyConstants;
import com.yxsd.kanshu.base.controller.BaseController;
import com.yxsd.kanshu.portal.model.DriveBook;
import com.yxsd.kanshu.portal.model.DriveBookCycle;
import com.yxsd.kanshu.portal.model.DriveType;
import com.yxsd.kanshu.portal.service.IDriveBookCycleService;
import com.yxsd.kanshu.portal.service.IDriveBookService;
import com.yxsd.kanshu.portal.service.IDriveTypeService;
import com.yxsd.kanshu.product.model.BookExpand;
import com.yxsd.kanshu.product.service.IBookExpandService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/6.
 */
@Controller
@Scope("prototype")
@RequestMapping("driveBookTask")
public class DriveBookTask extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DriveBookTask.class);

    @Resource(name = "masterRedisTemplate")
    private RedisTemplate<String,DriveBook> masterRedisTemplate;

    @Resource(name="driveBookService")
    IDriveBookService driveBookService;

    @Resource(name="driveBookCycleService")
    IDriveBookCycleService driveBookCycleService;

    @Resource(name="driveTypeService")
    IDriveTypeService driveTypeService;

    @Resource(name="bookExpandService")
    IBookExpandService bookExpandService;

    /**
     * 更换榜单图书 每天早晨5:20执行
     */
    public void changeDriveBook(){
        logger.info("开始更换榜单图书");
        Map<String,Object> condition = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date day = new Date();
        List<DriveType> driveTypes = this.driveTypeService.findListByParamsObjs(null);
        for(DriveType driveType : driveTypes){
            int i = driveType.getType();
            condition.clear();
            condition.put("type",i);
            condition.put("day",sdf.format(day));
            List<DriveBookCycle> driveBookCycles = this.driveBookCycleService.findListByParamsObjs(condition);
            if(CollectionUtils.isNotEmpty(driveBookCycles)){
                List<DriveBook> driveBooks = this.driveBookService.findListByParams("type",i,"manType",1);
                if(CollectionUtils.isNotEmpty(driveBooks)){
                    //删除旧榜单
                    for(DriveBook driveBookOld : driveBooks){
                        driveBookService.deleteById(driveBookOld.getId());
                        //清除缓存
                        String key = String.format(RedisKeyConstants.CACHE_DRIVE_BOOK_ONE_KEY,i,driveBookOld.getBookId(),1);
                        masterRedisTemplate.delete(key);
                    }
                }
                //更换新榜单
                for(DriveBookCycle driveBookCycle : driveBookCycles){
                    DriveBook driveBook = this.driveBookService.findUniqueByParams("bookId",driveBookCycle.getBookId(),"type",i,"status",1);
                    if(driveBook == null){
                        driveBook = new DriveBook();
                        driveBook.setManType(1);
                        driveBook.setStatus(1);
                        driveBook.setScore(driveBookCycle.getScore());
                        driveBook.setType(i);
                        driveBook.setBookId(driveBookCycle.getBookId());
                        driveBook.setNum(driveBookCycle.getNum());
                        driveBook.setCreateDate(new Date());
                        driveBook.setUpdateDate(new Date());
                        this.driveBookService.save(driveBook);
                    }else{
                        driveBook.setManType(1);
                        driveBook.setScore(driveBookCycle.getScore());
                        driveBook.setUpdateDate(new Date());
                        this.driveBookService.update(driveBook);
                    }
                }
                //清除缓存
                String key =String.format(RedisKeyConstants.CACHE_DRIVE_BOOK_KEY, i, 1);
                masterRedisTemplate.delete(key);
            }
        }
        //根据图书点击量更改免费图书的排序
        List<DriveBook> driveBooks = this.driveBookService.findListByParams("type",9,"manType",0);
        for(DriveBook driveBook : driveBooks){
            BookExpand bookExpand = bookExpandService.findUniqueByParams("bookId",driveBook.getBookId());
            if(bookExpand != null){
                driveBook.setScore(bookExpand.getClickNum().intValue());
                driveBook.setUpdateDate(new Date());
                this.driveBookService.update(driveBook);
            }
        }
        //清除缓存
        String key =String.format(RedisKeyConstants.CACHE_DRIVE_BOOK_KEY, 9, 1);
        masterRedisTemplate.delete(key);

        logger.info("结束更新榜单");
    }


    public static void main(String[] args) {
        DriveBookTask task = new DriveBookTask();
        //task.runChannelData();

    }
}
