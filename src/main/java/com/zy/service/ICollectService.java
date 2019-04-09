package com.zy.service;

import com.zy.domain.Collect;
import com.zy.domain.vo.CollectStageVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICollectService {
    public int insertCollect(Collect collect);

    public int updateCollect(Collect collect);

    public int deleteCollect(int id);

    public Collect getCollect(int id);

    public int deleteCollectByStageId(int userId, int scenarioId, int userStageTypeId, int stageId);

    public int deleteCollectByStageTypeId(int userId, int scenarioId, int userStageTypeId);

    public int deleteCollectByScenarioId(int userId, int scenarioId);

    public List<CollectStageVo> getStageForCollect(int userId, int scenarioId);

    public int updateStageType(int collectId,int userStageTypeId);
}