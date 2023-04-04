package com.lee.schoolhelper.service;

import com.lee.schoolhelper.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getAllNotice();
    List<Notice> getNoticeList(Integer currentPageNo,Integer pageSize);
    Integer countNotice();
    List<Notice> getNoticePage(Integer pageNo,Integer pageSize);
    Integer getNoticeNumber();
    Integer addNotice(Notice notice);
}
