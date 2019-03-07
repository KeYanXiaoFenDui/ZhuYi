package com.zy.service;

import com.zy.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(int id);

    public User getUser(int id);
}