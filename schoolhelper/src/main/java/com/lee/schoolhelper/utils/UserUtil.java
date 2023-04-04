package com.lee.schoolhelper.utils;

import cn.hutool.core.util.RandomUtil;
import com.lee.schoolhelper.entity.User;

import java.time.LocalDateTime;

public class UserUtil {
    public static User getDefaultUser(){
        User user = new User();
        user.setUserIcon("");
        user.setUserName("未填写");
        user.setUserNickName(RandomUtil.randomString(6));
        user.setGender(0);
        user.setInstitute("未填写");
        user.setGrade(0);
        user.setSpeciality("未填写");
        user.setClassNumber(0);
        user.setUserAccount(null);
        user.setUserPassword(null);
        user.setState(0);
        user.setCreateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setPhoneNumber("未填写");
        return user;
    }
}
