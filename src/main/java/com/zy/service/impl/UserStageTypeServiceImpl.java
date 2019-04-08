package com.zy.service.impl;

import com.zy.domain.UserStageType;
import com.zy.mapper.UserStageTypeMapper;
import com.zy.service.IUserStageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStageTypeServiceImpl implements IUserStageTypeService {
    @Autowired
    private UserStageTypeMapper userStageTypeMapper;

    @Override
    public int insertUserStageType(UserStageType userStageType) {
        return userStageTypeMapper.insertUserStageType(userStageType);
    }

    @Override
    public int updateUserStageType(UserStageType userStageType) {
        return userStageTypeMapper.updateUserStageType(userStageType);
    }

    @Override
    public int deleteUserStageType(int id) {
        return userStageTypeMapper.deleteUserStageType(id);
    }

    @Override
    public UserStageType getUserStageType(int id) {
        return userStageTypeMapper.getUserStageType(id);
    }
    @Override
    public List<UserStageType> getUserStageTypeByScenarioId(int scenarioId) {
        return userStageTypeMapper.getUserStageTypeByScenarioId(scenarioId);
    }

    @Override
    public int deleteUserStageType(int userId, int scenarioId) {
        return userStageTypeMapper.deleteUserStageType(userId,scenarioId);
    }
}