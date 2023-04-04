package com.lee.schoolhelper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.schoolhelper.dao.UserMapper;
import com.lee.schoolhelper.dto.UserDTO;
import com.lee.schoolhelper.entity.User;
import com.lee.schoolhelper.service.UserService;
import com.lee.schoolhelper.utils.R;
import com.lee.schoolhelper.utils.RegexUtils;
import com.lee.schoolhelper.utils.UserHolder;
import com.lee.schoolhelper.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        System.out.println(user);
        return user;
    }

    @Override
    public Integer addUser(User user) {
        Integer integer = userMapper.addUser(user);
        return integer;
    }

    @Override
    public Integer deleteUserById(Integer id) {
        Integer integer = userMapper.deleteUserById(id);
        return integer;
    }
    @Override
    public R getUser(Integer id){
        //1.查询用户
        User user = userMapper.getUserById(id);
        //2.判断用户是否为空
        if(user == null){
            //2.1 如果为空 返回错误信息
            return R.fail("用户不存在");
        }
        UserDTO userDTO = BeanUtil.toBean(user, UserDTO.class);
        return R.success(null,userDTO);
    }

    @Override
    public Integer updateUser(User user, Integer id) {
        user.setUpdateTime(LocalDateTime.now());
        Integer res = userMapper.updateUserById(user, id);
        return res;
    }

    @Override
    public R verifyUser(String userAccount, String userPassword) {
        User user = userMapper.getUserByAccount(userAccount);
        if(user == null){
            return R.fail("用户名或密码错误!!!");
        }
        if(!user.getUserPassword().equals(userPassword)) {
            return R.fail("用户名或密码错误!!!");
        }
        return R.success("验证通过",null);

    }

    @Override
    public R modifyPassword(String password,Integer id) {
        if(!RegexUtils.isPasswordInvalid(password)){
            return R.fail("密码格式为4到32位的字母加数字");
        }
        Integer res = userMapper.updateUserPassword(password,id);
        if(res <= 0){
            return R.fail("密码修改失败");
        }
        return R.success("密码修改成功",null);
    }

    @Override
    public User getUserByOrderId(Integer id) {
        User user = userMapper.getUserByOrderId(id);
        return user;

    }

    @Override
    public List<User> getAllUser() {
        List<User> allUser = userMapper.getAllUser();
        return allUser;

    }

    @Override
    public Integer countUser() {
        Integer userCount = userMapper.getUserNumber();
        return userCount;
    }

    @Override
    public List<User> getUserList(Integer currentPageNo, Integer pageSize) {
        Integer startIndex = (currentPageNo - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(startIndex,pageSize);
        List<User> users = userMapper.getUserPage(rowBounds);
        return users;
    }

    @Override
    public Integer updateUserStateById(Integer userId) {
        Integer res = userMapper.updateUserStateById(userId);
        return  res;
    }

    @Override
    public List<User> searchUser(String searchContent) {
        List<User> user = userMapper.searchUser(searchContent);
        return user;
    }

    @Override
    public R userRegister(String account, String password) {
        if(account == null || password == null){
            return R.fail("用户或密码为空");
        }
        if(!(RegexUtils.isAccountInvalid(account) && RegexUtils.isAccountInvalid(password))){
            return  R.fail("用户名或密码不符合则");
        }
        Integer integer = userMapper.countUserByAccount(account);
        if(integer > 0){
            return  R.fail("用户账号已存在");
        }
        User defaultUser = UserUtil.getDefaultUser();
        defaultUser.setUserAccount(account);
        defaultUser.setUserPassword(password);
        userMapper.addUser(defaultUser);
        return R.success("用户注测成功",null);
    }

    @Override
    public R userLogin(String account, String password, HttpSession session) {
        if(account == null || password == null){
            return R.fail("密码或账号为空");
        }
        User user = userMapper.getUserByAccount(account);
        if(user == null){
            return R.fail("账号或密码有误");
        }
        if (!StrUtil.equals(password,user.getUserPassword())) {
            return R.fail("账号或密码有误");
        }
        session.setAttribute("userInfo",user);
        Object userInfo = session.getAttribute("userInfo");
        log.info("userInfo is {}",userInfo);
        log.info("session is {}",session);
        return R.success("用户登录",user.getId());
    }

}
