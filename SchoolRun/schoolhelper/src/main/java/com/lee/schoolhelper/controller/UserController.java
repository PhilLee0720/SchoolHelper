package com.lee.schoolhelper.controller;

import com.lee.schoolhelper.dto.SearchBody;
import com.lee.schoolhelper.dto.UserDTO;
import com.lee.schoolhelper.entity.User;
import com.lee.schoolhelper.service.UserService;
import com.lee.schoolhelper.utils.R;
import com.lee.schoolhelper.utils.RegexUtils;
import com.lee.schoolhelper.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/get/{id}")
    public R getUser(@PathVariable("id")Integer id){
        return userService.getUser(id);
    }
    @PostMapping("/add")
    public R addUser(){
        User user = new User();
        user.setUserIcon("");
        user.setUserName("张奕");
        user.setUserNickName("macho");
        user.setGender(0);
        user.setInstitute("计算机学院");
        user.setGrade(2019);
        user.setSpeciality("计算机科学与技术");
        user.setClassNumber(1);
        user.setUserAccount("1260071140");
        user.setUserPassword("123456");
        user.setState(0);
        user.setCreateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        Integer integer = userService.addUser(user);
        if(integer < 0){
            return R.fail("用户添加失败");
        }
        return R.success("用户插入成功",null);
    }
    @DeleteMapping("/delete/{id}")
    public R deleteUser(@PathVariable("id")Integer id){
        Integer integer = userService.deleteUserById(id);
        if(integer < 0){
            return  R.fail("用户删除失败");
        }
        return R.success("用户删除成功",null);
    }
    @PostMapping("/register")
    public R register(@RequestParam("userAccount") String account,@RequestParam("userPassword") String password){
       log.info("Account is {}",account);
       log.info("Password is {}",password);
       return  userService.userRegister(account,password);
    }

    @GetMapping("/login")
    public R userLogin(@RequestParam("userAccount") String account, @RequestParam("userPassword") String password, HttpSession session){
        log.info("UserAccount is {}",account);
        log.info("UserPassword is {}",password);
        return userService.userLogin(account,password,session);
    }
    @PostMapping("/update/{userId}")
    public R updateUser(@RequestBody User user,@PathVariable("userId")Integer userId){
        if(!RegexUtils.isPhoneInvalid(user.getPhoneNumber())){
            return R.fail("请输入正确的手机号");
        }
        Integer res = userService.updateUser(user, userId);
        if(res <= 0){
            return R.fail("用户更新失败，请稍后再试");
        }
        return R.success("用户更新成功",null);
    }
    @GetMapping("/verify")
    public R verifyUser(@RequestParam("userAccount")String userAccount,@RequestParam("userPassword")String userPassword){
        R r = userService.verifyUser(userAccount, userPassword);
        return r;
    }

    @PutMapping("/modiPwd/{password}")
    public R verifyUser(@PathVariable("password")String password,HttpSession session){
        R r = userService.modifyPassword(password,UserHolder.get().getId());
        if(r.getCode() == 200){
            session.removeAttribute("userInfo");
        }
        return r;
    }

    @GetMapping("/getByOrderId/{orderId}")
    public R getUserByOrderId(@PathVariable("orderId")Integer id){
        User  user = userService.getUserByOrderId(id);
        if(user == null){
            return R.fail("当前订单无人接收");
        }
        return R.success("",user);
    }
    @GetMapping("/logout")
    public R logout(HttpSession session){
        UserHolder.remove();
        session.removeAttribute("userInfo");
        return R.success("",null);
    }
    @GetMapping("/getAll")
    public R queryAllUser(){
        List<User> users = userService.getAllUser();
        if(users == null || users.size() <= 0){
            return R.fail("");
        }
        return R.success("",users);

    }
    @GetMapping("/count")
    public R count(){
        Integer res = userService.countUser();
        return R.success("",res);

    }
    @GetMapping("/getUserList/{pageNo}/{pageSize}")
    public R getUserList(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        List<User> users = userService.getUserList(pageNo, pageSize);
        if(users == null || users.size() <= 0){
            return R.fail("");
        }else{
            return R.success("",users);
        }
    }

    @PutMapping("/check/{userId}")
    public  R checkUser(@PathVariable("userId")Integer userId){
        Integer res = userService.updateUserStateById(userId);
        if(res <= 0){
            return R.fail("审核失败");
        }
        return R.success("审核成功",null);

    }
    @PostMapping("/query")
    public R searchUser(@RequestBody SearchBody searchBody){
        List<User> users = userService.searchUser(searchBody.getContent());
        if(users == null || users.size() <= 0){
            return  R.fail("用户不存在");
        }
        return  R.success("",users);
    }
}
