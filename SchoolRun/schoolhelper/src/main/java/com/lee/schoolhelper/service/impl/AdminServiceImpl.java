package com.lee.schoolhelper.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lee.schoolhelper.dao.AdminMapper;
import com.lee.schoolhelper.entity.Admin;
import com.lee.schoolhelper.service.AdminService;
import com.lee.schoolhelper.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public R login(String account, String password) {
        Admin admin = adminMapper.getAdminByAdminAccount(account);
        if(admin == null){
            return  R.fail("账号或密码有误");
        }
        String admPassword = admin.getAdmPassword();
        if(!StrUtil.equals(admPassword,password)){
            return R.fail("账号密码有误");
        }
        return R.success("登录成功",null);
    }
}
