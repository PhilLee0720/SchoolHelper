package com.lee.schoolhelper.controller;

import com.lee.schoolhelper.entity.Notice;
import com.lee.schoolhelper.service.NoticeService;
import com.lee.schoolhelper.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;
    @GetMapping("/get")
    public R getAllNotice(){
        List<Notice> notices = noticeService.getAllNotice();
        if(notices == null || notices.size() < 0){
            return R.fail("");
        }
        return R.success("",notices);
    }
    @GetMapping("/getPage/{currentPageNo}/{pageSize}")
    public R getPageList(Integer currentPageNo,Integer pageSize){
        List<Notice> noticeList = noticeService.getNoticeList(currentPageNo, pageSize);
        if(noticeList == null || noticeList.size() <= 0){
            return R.fail("");
        }
        return R.success(null,noticeList);
    }
    @GetMapping("/count")
    public R getNoticeCount(){
        Integer res = noticeService.countNotice();
        return R.success("",res);

    }
    @GetMapping("/page/{pageNo}/{pageSize}")
    public R getNoticePage(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        List<Notice> noticePage = noticeService.getNoticePage(pageNo, pageSize);
        if(noticePage == null || noticePage.size() <= 0){
            return R.fail("null");
        }
        return R.success("",noticePage);
    }
    @PostMapping("/add")
    public R add(@RequestBody Notice notice){
        Integer res = noticeService.addNotice(notice);
        if(res <= 0){
            return R.fail("发布失败");
        }
        return R.success("发布成功",null);
    }
}
