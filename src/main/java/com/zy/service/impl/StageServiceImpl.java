package com.zy.service.impl;

import com.zy.domain.Stage;
import com.zy.domain.vo.StageAuditVo;
import com.zy.domain.vo.StageRequestVo;
import com.zy.mapper.StageMapper;
import com.zy.service.IStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StageServiceImpl implements IStageService {
    @Autowired
    private StageMapper stageMapper;

    @Override
    public int insertStage(Stage stage) {
        return stageMapper.insertStage(stage);
    }

    @Override
    public int updateStage(Stage stage) {
        return stageMapper.updateStage(stage);
    }

    @Override
    public int deleteStage(int id) {
        return stageMapper.deleteStage(id);
    }

    @Override
    public Stage getStage(int id) {
        return stageMapper.getStage(id);
    }

    @Override
    public StageAuditVo getStageAudit(int id) {
        return stageMapper.getStageAudit(id);
    }

    @Override
    public List<Map> getUserStageList(int userId, int processStatus, String idOrName) {
        return stageMapper.getUserStageList(userId, processStatus, idOrName);
    }

    @Override
    public List<Stage> getStageList(StageRequestVo vo) {
        return stageMapper.getStageList(vo);
    }

}