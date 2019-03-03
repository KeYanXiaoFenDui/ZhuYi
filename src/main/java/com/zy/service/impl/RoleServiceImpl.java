package com.zy.service.impl;


import com.zy.domain.Role;
import com.zy.mapper.RoleMapper;
import com.zy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRole(int id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public Role getRole(int id) {
        return roleMapper.getRole(id);
    }
}