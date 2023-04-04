package com.lee.schoolhelper.service.impl;

import com.lee.schoolhelper.dao.LeaveWordMapper;
import com.lee.schoolhelper.entity.LeaveWord;
import com.lee.schoolhelper.service.LeaveWordService;
import com.lee.schoolhelper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Service
public class LeaveWordServiceImpl implements LeaveWordService {
    @Resource
    private LeaveWordMapper leaveWordMapper;
    @Override
    public List<LeaveWord> getLeaveWordByPage(Integer pageNo, Integer pageSize) {
        Integer startIndex = (pageNo-1) * pageSize;
        RowBounds rowBounds = new RowBounds(startIndex, pageSize);
        List<LeaveWord> leaveWords = leaveWordMapper.getLeaveWordByPage(rowBounds);
        return leaveWords;
    }

    @Override
    public Integer getTotal() {
        Integer total = leaveWordMapper.getTotal();
        return total;

    }

    @Override
    public Integer addLeaveWord(LeaveWord leaveWord,Integer userId) {
        Integer integer = leaveWordMapper.addLeaveWord(leaveWord,userId);
        log.info("LeaveWord is {}",leaveWord);
        return integer;
    }

    @Override
    public Integer deleteLeaveWord(Integer id) {
        Integer res = leaveWordMapper.removeLeaveWord(id);
        return res;
    }
}
