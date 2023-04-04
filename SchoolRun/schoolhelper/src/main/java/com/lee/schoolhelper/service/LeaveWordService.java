package com.lee.schoolhelper.service;

import com.lee.schoolhelper.entity.LeaveWord;
import com.lee.schoolhelper.service.UserService;

import java.util.List;

public interface LeaveWordService {
    List<LeaveWord> getLeaveWordByPage(Integer pageNo, Integer pageSize);
    Integer getTotal();
    Integer addLeaveWord(LeaveWord leaveWord,Integer userId);
    Integer deleteLeaveWord(Integer id);
}
