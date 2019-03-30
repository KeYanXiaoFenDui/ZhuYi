package com.zy.service;

import com.zy.domain.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IUserRequestService {
    public int insertUserRequest(UserRequest userRequest);

    public int updateUserRequest(UserRequest userRequest);

    public int deleteUserRequest(int id);

    public UserRequest getUserRequest(int id);

    public List<Map> getUserRequestList(int id,int reqStatus, String filmName, String startTime, String endTime);
}