package com.lee.schoolhelper.utils;

import com.lee.schoolhelper.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();
    public static void set(UserDTO userDTO){
        tl.set(userDTO);
    }
    public static UserDTO get(){
        return tl.get();
    }
    public static  void remove(){
        tl.remove();
    }
}
