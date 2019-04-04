package com.zy.service;

import com.zy.domain.Stage;
import com.zy.domain.vo.StageAuditVo;
import com.zy.domain.vo.StageRequestVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IStageService {
    public int insertStage(Stage stage);

    public int updateStage(Stage stage);

    public int deleteStage(int id);

    public Stage getStage(int id);

    public StageAuditVo getStageAudit(int id);

    public List<Map> getUserStageList(int userId,int processStatus,String idOrName);

    public List<Stage> getStageList(StageRequestVo vo);
}