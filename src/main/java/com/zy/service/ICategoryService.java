package com.zy.service;

import com.zy.domain.Category;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryService {
    public int insertCategory(Category category);

    public int updateCategory(Category category);

    public int deleteCategory(int id);

    public Category getCategory(int id);
}