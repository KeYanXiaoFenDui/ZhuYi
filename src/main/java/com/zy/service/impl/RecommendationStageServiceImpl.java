package com.zy.service.impl;

import com.zy.domain.RecommendationStage;
import com.zy.mapper.RecommendationStageMapper;
import com.zy.service.IRecommendationStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationStageServiceImpl implements IRecommendationStageService {
    @Autowired
    private RecommendationStageMapper recommendationStageMapper;

    @Override
    public int insertRecommendationStage(RecommendationStage recommendationStage) {
        return recommendationStageMapper.insertRecommendationStage(recommendationStage);
    }

    @Override
    public int updateRecommendationStage(RecommendationStage recommendationStage) {
        return recommendationStageMapper.updateRecommendationStage(recommendationStage);
    }

    @Override
    public int updateRecommendationStageCase(int operateAdminId, int id) {
        return recommendationStageMapper.updateRecommendationStageCase(operateAdminId, id);
    }

    @Override
    public int deleteRecommendationStage(int id) {
        return recommendationStageMapper.deleteRecommendationStage(id);
    }

    @Override
    public RecommendationStage getRecommendationStage(int id) {
        return recommendationStageMapper.getRecommendationStage(id);
    }
}