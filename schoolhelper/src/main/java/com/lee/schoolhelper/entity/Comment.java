package com.lee.schoolhelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Comment")
public class Comment implements Serializable {
    private Integer id;
    private String commentContent;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private User releasedUser;
    private User acceptedUser;

}
