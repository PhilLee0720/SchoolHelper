package com.lee.schoolhelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Order")
public class Order {
    private Integer id;
    private String orderSerial;
    private String orderMain;
    private String  orderDesc;
    private String  orderNote;
    private Integer orderCheckState;
    private Integer orderTransState;
    private Integer orderPrice;
    private User releasedUser;
    private User acceptedUser;
    private LocalDateTime beginTime;
    private String doAddress;
    private String sendAddress;
    private Integer goodsWeight;
    private Category category;
    private String phoneNumber;
    private String createTime;
    private String updateTime;

}
