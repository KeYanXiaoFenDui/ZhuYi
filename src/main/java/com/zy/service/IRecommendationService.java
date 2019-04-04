package com.zy.service;

import com.zy.domain.Recommendation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IRecommendationService {
    public int insertRecommendation(Recommendation recommendation);

    public int updateRecommendation(Recommendation recommendation);

    public int deleteRecommendation(int id);

    public Recommendation getRecommendation(int id);

    public List<Map> getRecommendListById(int reqId);

    public List<Map> getRecommendationDetail(int reqId);

    public List<Map> getRecommendStageList(int id);
}