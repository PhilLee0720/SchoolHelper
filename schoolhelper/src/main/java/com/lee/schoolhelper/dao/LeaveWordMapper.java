package com.lee.schoolhelper.dao;

import com.lee.schoolhelper.entity.LeaveWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface LeaveWordMapper {
    List<LeaveWord> getLeaveWordByPage(RowBounds rowBounds);
    Integer getTotal();
    Integer addLeaveWord(@Param("leaveWord") LeaveWord leaveWord,@Param("userId") Integer userId);
    Integer removeLeaveWord(@Param("leaveWordId")Integer id);
}
