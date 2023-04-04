package com.lee.schoolhelper.dao;

import com.lee.schoolhelper.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> getAllNotice();
    List<Notice> getNoticeByPage(RowBounds rowBounds);
    Integer getNoticeCount();
    List<Notice> getNoticePage(RowBounds rowBounds);
    Integer getNoticeNumber();
    Integer addNotice(Notice notice);
}
