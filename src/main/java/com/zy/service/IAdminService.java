package com.zy.service;


import com.zy.domain.Admin;
import org.springframework.stereotype.Service;

@Service
public interface IAdminService {
    public int insertAdmin(Admin admin);

    public int updateAdmin(Admin admin);

    public int deleteAdmin(int id);

    public Admin getAdmin(int id);
}