package com.lee.schoolhelper.controller;

import com.lee.schoolhelper.service.AdminService;
import com.lee.schoolhelper.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @GetMapping("/login")
    public R login(@RequestParam("username") String account, @RequestParam("password") String password){
        return adminService.login(account, password);
    }
}
