package com.zy.service;

import com.zy.domain.UserStageType;
import org.springframework.stereotype.Service;

@Service
public interface IUserStageTypeService {
    public int insertUserStageType(UserStageType userStageType);

    public int updateUserStageType(UserStageType userStageType);

    public int deleteUserStageType(int id);

    public UserStageType getUserStageType(int id);
}