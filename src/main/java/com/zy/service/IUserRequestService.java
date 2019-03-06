package com.zy.service;

import com.zy.domain.UserRequest;
import org.springframework.stereotype.Service;

@Service
public interface IUserRequestService {
    public int insertUserRequest(UserRequest userRequest);

    public int updateUserRequest(UserRequest userRequest);

    public int deleteUserRequest(int id);

    public UserRequest getUserRequest(int id);
}