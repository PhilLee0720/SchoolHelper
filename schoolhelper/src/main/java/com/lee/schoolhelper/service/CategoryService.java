package com.lee.schoolhelper.service;

import com.lee.schoolhelper.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory();
    Integer addCategory(Category category);
}
