package com.lee.schoolhelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Admin")
public class Admin {
    private Integer idp;
    private String admName;
    private String admAccount;
    private String admPassword;
    private String admPhoneNumber;
    private String admEmail;
}
