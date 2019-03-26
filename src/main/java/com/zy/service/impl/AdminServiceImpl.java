package com.zy.service.impl;


import com.zy.domain.Admin;
import com.zy.mapper.AdminMapper;
import com.zy.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int deleteAdmin(int id) {
        return adminMapper.deleteAdmin(id);
    }

    @Override
    public Admin getAdmin(int id) {
        return adminMapper.getAdmin(id);
    }

    @Override
    public List<Map> getAdminList(String accountOrName) {
        return adminMapper.getAdminList(accountOrName);
    }

    @Override
    public Admin adminLogin(String account, String password) {
        return adminMapper.adminLogin(account, password);
    }
}