package com.zy.service;

import com.zy.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(int id);

    public User getUser(int id);

    public List<User> getUserList(String nameOrAccount, String startTime, String endTime);
}