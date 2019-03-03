package com.zy.service.impl;


import com.zy.domain.Menu;
import com.zy.mapper.MenuMapper;
import com.zy.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenu(int id) {
        return menuMapper.deleteMenu(id);
    }

    @Override
    public Menu getMenu(int id) {
        return menuMapper.getMenu(id);
    }
}