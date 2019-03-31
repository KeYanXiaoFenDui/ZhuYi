package com.zy.service.impl;

import com.zy.domain.Stage;
import com.zy.enums.StageStatus;
import com.zy.mapper.StageMapper;
import com.zy.service.IStageService;
import com.zy.vo.StageQueryListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Stage> findByQueryVo(StageQueryListVo listVo) {
        return stageMapper.findByQueryVo(listVo);
    }
}