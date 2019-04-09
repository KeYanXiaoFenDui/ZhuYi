package com.zy.service.impl;

import com.zy.domain.User;
import com.zy.mapper.UserMapper;
import com.zy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> getUserList(String nameOrAccount, String startTime, String endTime) {
        return userMapper.getUserList(nameOrAccount, startTime, endTime);
    }

    @Override
    public int disableUser(int id) {
        return userMapper.disableUser(id);
    }

    @Override
    public User loginForC(String account, String password) {
        return userMapper.loginForC(account,password);
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }
}