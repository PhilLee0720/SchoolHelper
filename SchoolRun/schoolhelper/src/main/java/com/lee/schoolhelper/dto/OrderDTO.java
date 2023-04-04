package com.lee.schoolhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private String orderMain;
    private String orderDesc;
    private Integer orderPrice;
    private LocalDateTime beginTime;
}
