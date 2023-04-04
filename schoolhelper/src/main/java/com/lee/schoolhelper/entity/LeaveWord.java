package com.lee.schoolhelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("LeaveWord")
public class LeaveWord {
    private Integer id;
    private String leaveWordContent;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private User releasedUser;

}
