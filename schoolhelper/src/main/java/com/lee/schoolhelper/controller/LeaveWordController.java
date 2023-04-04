package com.lee.schoolhelper.controller;

import com.lee.schoolhelper.dto.UserDTO;
import com.lee.schoolhelper.entity.LeaveWord;
import com.lee.schoolhelper.service.LeaveWordService;
import com.lee.schoolhelper.utils.R;
import com.lee.schoolhelper.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/leaveword")
public class LeaveWordController {
    @Resource
    private LeaveWordService leaveWordService;
    @GetMapping("/listPage/{pageNo}/{pageSize}")
    public R listPage(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize")Integer pageSize){
        if(pageNo == null || pageSize == null){
            return R.fail("");
        }
        List<LeaveWord> leaveWordByPage = leaveWordService.getLeaveWordByPage(pageNo, pageSize);
        if(leaveWordByPage == null || leaveWordByPage.size() == 0){
            return R.fail("留言为空");
        }
        return R.success("",leaveWordByPage);
    }
    @GetMapping("/getTotal")
    public R getTotal(){
        Integer total = leaveWordService.getTotal();
        if(total == null){
            return R.fail("");
        }
        return R.success("",total);
    }
    @PostMapping("/add")
    public R addLeaveWord(@RequestBody LeaveWord leaveWord){
        UserDTO userDTO = UserHolder.get();
        log.info("user id is {}",userDTO.getId());
        log.info("LeaveWordContent is {}",leaveWord);
        Integer integer = leaveWordService.addLeaveWord(leaveWord,userDTO.getId());
        if(integer < 1){
            return R.fail("留言失败");
        }
        return R.success("留言成功",null);
    }
    @DeleteMapping("/delete/{id}")
    public R removeLeaveWord(@PathVariable("id") Integer id){
        Integer res = leaveWordService.deleteLeaveWord(id);
        if(res <= 0){
            return R.fail("删除失败");
        }
        return R.success("删除成功",null);
    }
}
