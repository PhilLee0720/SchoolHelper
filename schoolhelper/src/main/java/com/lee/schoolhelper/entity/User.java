package com.lee.schoolhelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("User")
public class User {
    private Integer id;
    private String userIcon;
    private String userName;
    private String userNickName;
    private Integer gender;
    private String institute;
    private Integer grade;
    private String speciality;
    private Integer classNumber;
    private String userAccount;
    private String userPassword;
    private Integer state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String phoneNumber;
    private Integer role;
}
