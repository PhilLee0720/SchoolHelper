package com.lee.schoolhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String userNickName;
    private String userName;
    private Integer gender;
    private String institute;
    private String speciality;
    private Integer grade;
    private Integer classNumber;
    private String phoneNumber;
}
