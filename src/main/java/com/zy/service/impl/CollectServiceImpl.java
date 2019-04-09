package com.zy.service.impl;

import com.zy.domain.Collect;
import com.zy.domain.vo.CollectStageVo;
import com.zy.mapper.CollectMapper;
import com.zy.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements ICollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public int insertCollect(Collect collect) {
        return collectMapper.insertCollect(collect);
    }

    @Override
    public int updateCollect(Collect collect) {
        return collectMapper.updateCollect(collect);
    }

    @Override
    public int deleteCollect(int id) {
        return collectMapper.deleteCollect(id);
    }

    @Override
    public Collect getCollect(int id) {
        return collectMapper.getCollect(id);
    }

    @Override
    public int deleteCollectByStageId(int userId, int scenarioId, int userStageTypeId, int stageId) {
        return collectMapper.deleteCollectByStageId(userId,scenarioId,userStageTypeId,stageId);
    }

    @Override
    public int deleteCollectByStageTypeId(int userId, int scenarioId, int userStageTypeId) {
        return collectMapper.deleteCollectByStageTypeId(userId, scenarioId, userStageTypeId);
    }

    @Override
    public int deleteCollectByScenarioId(int userId, int scenarioId) {
        return collectMapper.deleteCollectByScenarioId(userId, scenarioId);
    }

    @Override
    public List<CollectStageVo> getStageForCollect(int userId, int scenarioId) {
        return collectMapper.getStageForCollect(userId, scenarioId);
    }

    @Override
    public int updateStageType(int collectId, int userStageTypeId) {
        return collectMapper.updateStageType(collectId, userStageTypeId);
    }
}