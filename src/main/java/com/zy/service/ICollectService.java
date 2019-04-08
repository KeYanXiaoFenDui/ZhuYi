package com.zy.service;

import com.zy.domain.Collect;
import org.springframework.stereotype.Service;

@Service
public interface ICollectService {
    public int insertCollect(Collect collect);

    public int updateCollect(Collect collect);

    public int deleteCollect(int id);

    public Collect getCollect(int id);
}