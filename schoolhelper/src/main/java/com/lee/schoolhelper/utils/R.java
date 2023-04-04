package com.lee.schoolhelper.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*统一结果返回类*/
@Data
public class R<T> {
    String message; //消息 自定义
    Integer code;//代码 200为成功 400及其他为失败
    T data;
    public R(String message,Integer code,T data){
        this.message = message;
        this.code = code;
        this.data = data;
    }
    public static <T> R<T> success(String message,T data){
        Integer code = 200;
        return  new R(message,code,data);
    }
    public static <T> R <T> fail(String message){
        Integer code = 400;
        return  new R(message,code,null);
    }
}
