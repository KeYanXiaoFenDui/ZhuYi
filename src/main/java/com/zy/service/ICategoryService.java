package com.zy.service;

import com.github.pagehelper.Page;
import com.zy.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    public int insertCategory(Category category);

    public int updateCategory(Category category);

    public int deleteCategory(int id);

    public Category getCategory(int id);

    public List<Category> findByNameLike(String name,int type,int level);

    public List<Category> findByLevelAndType(int level,int type);

    public int cascadeDeleteCategory(int id);
}