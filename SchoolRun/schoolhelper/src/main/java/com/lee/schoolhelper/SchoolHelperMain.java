package com.lee.schoolhelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*",allowCredentials = "true")
@MapperScan("com.lee.schoolhelper.dao")
@EnableScheduling
public class SchoolHelperMain {
    public static void main(String[] args) {
        SpringApplication.run(SchoolHelperMain.class,args);
    }
}
