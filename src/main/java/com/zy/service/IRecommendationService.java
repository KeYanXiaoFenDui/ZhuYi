package com.zy.service;

import com.zy.domain.Recommendation;
import org.springframework.stereotype.Service;

@Service
public interface IRecommendationService {
    public int insertRecommendation(Recommendation recommendation);

    public int updateRecommendation(Recommendation recommendation);

    public int deleteRecommendation(int id);

    public Recommendation getRecommendation(int id);
}