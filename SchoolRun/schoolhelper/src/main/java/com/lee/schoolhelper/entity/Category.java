package com.lee.schoolhelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String categoryName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
