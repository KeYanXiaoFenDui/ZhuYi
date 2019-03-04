package com.zy.service.impl;

import com.zy.domain.Category;
import com.zy.mapper.CategoryMapper;
import com.zy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int deleteCategory(int id) {
        return categoryMapper.deleteCategory(id);
    }

    @Override
    public Category getCategory(int id) {
        return categoryMapper.getCategory(id);
    }
}