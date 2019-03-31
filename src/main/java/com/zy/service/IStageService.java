package com.zy.service;

import com.zy.domain.Stage;
import com.zy.enums.StageStatus;
import com.zy.vo.StageQueryListVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStageService {
    public int insertStage(Stage stage);

    public int updateStage(Stage stage);

    public int deleteStage(int id);

    public Stage getStage(int id);

    public List<Stage> findByQueryVo(StageQueryListVo listVo);
}