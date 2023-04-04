package com.lee.schoolhelper.service;

import com.lee.schoolhelper.entity.User;
import com.lee.schoolhelper.utils.R;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    public User getUserById(Integer id);
    public Integer addUser(User user);
    public Integer deleteUserById(Integer id);
    public R userRegister(String account, String Password);
    public R userLogin(String account, String password, HttpSession httpSession);
    public R getUser(Integer id);
    public Integer updateUser(User user,Integer id);
    public R verifyUser(String userAccount,String userPassword);
    public R modifyPassword(String password,Integer id);
    public User getUserByOrderId(Integer id);
    public List<User> getAllUser();
    Integer countUser();
    List<User> getUserList(Integer currentPageNo,Integer pageSize);
    Integer updateUserStateById(Integer userId);
    List<User> searchUser(String searchContent);
}
