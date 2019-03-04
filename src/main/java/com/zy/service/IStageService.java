package com.zy.service;

import com.zy.domain.Stage;
import org.springframework.stereotype.Service;

@Service
public interface IStageService {
    public int insertStage(Stage stage);

    public int updateStage(Stage stage);

    public int deleteStage(int id);

    public Stage getStage(int id);
}