package com.lee.schoolhelper.service.impl;

import com.lee.schoolhelper.dao.NoticeMapper;
import com.lee.schoolhelper.entity.Comment;
import com.lee.schoolhelper.entity.Notice;
import com.lee.schoolhelper.service.NoticeService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    @Override
    public List<Notice> getAllNotice() {
        List<Notice> notices = noticeMapper.getAllNotice();
        return notices;
    }

    @Override
    public List<Notice> getNoticeList(Integer currentPageNo, Integer pageSize) {
        Integer startIndex = (currentPageNo-1) * pageSize;
        RowBounds rowBounds = new RowBounds(startIndex, pageSize);
        List<Notice> notices = noticeMapper.getNoticeByPage(rowBounds);
        return notices;
    }

    @Override
    public Integer countNotice() {
        Integer number = noticeMapper.getNoticeCount();
        return number;
    }

    @Override
    public List<Notice> getNoticePage(Integer pageNo, Integer pageSize) {
        Integer startIndex = (pageNo - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(startIndex,pageSize);
        List<Notice> notices = noticeMapper.getNoticePage(rowBounds);
        return notices;
    }

    @Override
    public Integer getNoticeNumber() {
        Integer res = noticeMapper.getNoticeNumber();
        return res;
    }

    @Override
    public Integer addNotice(Notice notice) {
        Integer res = noticeMapper.addNotice(notice);
        return res;
    }
}
