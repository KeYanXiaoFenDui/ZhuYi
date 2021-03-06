package com.zy.service.impl;

import com.github.pagehelper.Page;
import com.zy.domain.Category;
import com.zy.mapper.CategoryMapper;
import com.zy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Category> findByNameLike(String name,int type,int level) {
        return categoryMapper.findByNameLike(name,type,level);
    }
    @Override
    public List<Category> findByLevelAndType(int level,int type) {
        return categoryMapper.findByLevelAndType(level,type);
    }
    @Override
    public int cascadeDeleteCategory(int id) {
        return categoryMapper.cascadeDeleteCategory(id);
    }

    @Override
    public List<Category> findSubByNameLike(String parentId, String name,int type) {
        return categoryMapper.findSubByNameLike( parentId, name,type);
    }

    @Override
    public List<Category> getStage1stCategory() {
        return categoryMapper.getStage1stCategory();
    }

    @Override
    public List<Category> getSubCategoryList(int id) {
        return categoryMapper.getSubCategoryList(id);
    }

    @Override
    public List<Map> getCategoryMsg() {
        return categoryMapper.getCategoryMsg();
    }
}