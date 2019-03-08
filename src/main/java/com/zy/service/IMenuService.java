package com.zy.service;


import com.zy.domain.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IMenuService {
    public int insertMenu(Menu menu);

    public int updateMenu(Menu menu);

    public int deleteMenu(int id);

    public Menu getMenu(int id);

    public List<Map> getMenuList();
}