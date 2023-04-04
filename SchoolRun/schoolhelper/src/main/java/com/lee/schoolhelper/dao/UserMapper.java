package com.lee.schoolhelper.dao;

import com.lee.schoolhelper.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserById(@Param("userId") Integer id);
    List<User> getAllUser();
    Integer addUser(User user);
    Integer deleteUserById(@Param("userId") Integer id);
    Integer countUserByAccount(@Param("userAccount") String account);
    User getUserByAccount(@Param("userAccount") String userAccount);
    Integer getUserId(@Param("userNickName")String userNickName);
    Integer updateUserById(@Param("user")User user,@Param("userId")Integer userId);
    Integer updateUserPassword(@Param("password")String password,@Param("id")Integer userId);
    User getUserByOrderId(@Param("orderId")Integer id);
    Integer getUserNumber();
    List<User>  getUserPage(RowBounds rowBounds);
    Integer updateUserStateById(@Param("userId") Integer userId);
    List<User> searchUser(@Param("searchContent")String content);
}
