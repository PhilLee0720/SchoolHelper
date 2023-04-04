package com.lee.schoolhelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Notice")
public class Notice {
    private Integer id;
    private String noticeContent;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
