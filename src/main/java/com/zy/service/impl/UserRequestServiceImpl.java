package com.zy.service.impl;

import com.zy.domain.UserRequest;
import com.zy.mapper.UserRequestMapper;
import com.zy.service.IUserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRequestServiceImpl implements IUserRequestService {
    @Autowired
    private UserRequestMapper userRequestMapper;

    @Override
    public int insertUserRequest(UserRequest userRequest) {
        return userRequestMapper.insertUserRequest(userRequest);
    }

    @Override
    public int updateUserRequest(UserRequest userRequest) {
        return userRequestMapper.updateUserRequest(userRequest);
    }

    @Override
    public int deleteUserRequest(int id) {
        return userRequestMapper.deleteUserRequest(id);
    }

    @Override
    public UserRequest getUserRequest(int id) {
        return userRequestMapper.getUserRequest(id);
    }
}