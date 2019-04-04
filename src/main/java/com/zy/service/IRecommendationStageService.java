package com.zy.service;

import com.zy.domain.RecommendationStage;
import org.springframework.stereotype.Service;

@Service
public interface IRecommendationStageService {
    public int insertRecommendationStage(RecommendationStage recommendationStage);

    public int updateRecommendationStage(RecommendationStage recommendationStage);

    public int updateRecommendationStageCase(int operateAdminId,int id);

    public int deleteRecommendationStage(int id);

    public RecommendationStage getRecommendationStage(int id);
}