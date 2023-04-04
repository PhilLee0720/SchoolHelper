package com.lee.schoolhelper.service.impl;

import com.lee.schoolhelper.dao.CategoryMapper;
import com.lee.schoolhelper.entity.Category;
import com.lee.schoolhelper.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = categoryMapper.getAllCategory();
        return categories;
    }

    @Override
    public Integer addCategory(Category category){
        Integer  res = categoryMapper.addCategory(category);
        return res;
    }
}
