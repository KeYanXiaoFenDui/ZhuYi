package com.zy.service.impl;

import com.zy.domain.Collect;
import com.zy.mapper.CollectMapper;
import com.zy.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int deleteCollect(int userId, int scenarioId, int userStageTypeId, int stageId) {
        return collectMapper.deleteCollect(userId,scenarioId,userStageTypeId,stageId);
    }
}