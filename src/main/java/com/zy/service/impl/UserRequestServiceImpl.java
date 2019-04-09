package com.zy.service.impl;

import com.zy.domain.UserRequest;
import com.zy.domain.vo.RecStageVo;
import com.zy.mapper.UserRequestMapper;
import com.zy.service.IUserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map> getUserRequestList(int id,int reqStatus, String filmName, String startTime, String endTime) {
        return userRequestMapper.getUserRequestList(id,reqStatus, filmName, startTime, endTime);
    }

    @Override
    public List<Map> getRecommendationForC(int userId) {
        return userRequestMapper.getRecommendationForC(userId);
    }

    @Override
    public List<RecStageVo> getStageListForRecommend(int reqId) {
        return userRequestMapper.getStageListForRecommend(reqId);
    }

    @Override
    public List<UserRequest> myRequest(int userId, int reqStatus) {
        return userRequestMapper.myRequest(userId, reqStatus);
    }
}