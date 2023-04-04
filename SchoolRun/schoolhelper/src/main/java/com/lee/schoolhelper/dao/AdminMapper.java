package com.lee.schoolhelper.dao;

import com.lee.schoolhelper.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    Admin getAdminByAdminAccount(@Param("admAccount")String account);
}
