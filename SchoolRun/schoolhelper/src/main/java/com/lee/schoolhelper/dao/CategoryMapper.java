package com.lee.schoolhelper.dao;

import com.lee.schoolhelper.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CategoryMapper {
     List<Category> getAllCategory();

     Integer addCategory(Category category);
}
