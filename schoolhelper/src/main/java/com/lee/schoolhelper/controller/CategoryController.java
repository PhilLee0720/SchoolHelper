package com.lee.schoolhelper.controller;

import com.lee.schoolhelper.entity.Category;
import com.lee.schoolhelper.service.CategoryService;
import com.lee.schoolhelper.service.UserService;
import com.lee.schoolhelper.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类控制层
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping("/get")
    public R getCategory(){
        List<Category> allCategory = categoryService.getAllCategory();
        if(allCategory == null){
            return R.fail("null");
        }
        return R.success("null",allCategory);

    }
    @PostMapping("/add")
    public R addCategory(@RequestBody Category category){
        Integer res = categoryService.addCategory(category);
        if(res <= 0){
            return  R.fail("添加失败");
        }
        return R.success("添加成功",null);



    }
}
