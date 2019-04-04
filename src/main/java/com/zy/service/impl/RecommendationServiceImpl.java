package com.zy.service.impl;

import com.zy.domain.Recommendation;
import com.zy.mapper.RecommendationMapper;
import com.zy.service.IRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendationServiceImpl implements IRecommendationService {
    @Autowired
    private RecommendationMapper recommendationMapper;

    @Override
    public int insertRecommendation(Recommendation recommendation) {
        return recommendationMapper.insertRecommendation(recommendation);
    }

    @Override
    public int updateRecommendation(Recommendation recommendation) {
        return recommendationMapper.updateRecommendation(recommendation);
    }

    @Override
    public int deleteRecommendation(int id) {
        return recommendationMapper.deleteRecommendation(id);
    }

    @Override
    public Recommendation getRecommendation(int id) {
        return recommendationMapper.getRecommendation(id);
    }

    @Override
    public List<Map> getRecommendListById(int reqId) {
        return recommendationMapper.getRecommendListById(reqId);
    }

    @Override
    public List<Map> getRecommendationDetail(int reqId) {
        return recommendationMapper.getRecommendationDetail(reqId);
    }

    @Override
    public List<Map> getRecommendStageList(int id) {
        return recommendationMapper.getRecommendStageList(id);
    }
}