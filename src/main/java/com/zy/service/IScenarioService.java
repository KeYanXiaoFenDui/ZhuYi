package com.zy.service;

import com.zy.domain.Scenario;
import org.springframework.stereotype.Service;

@Service
public interface IScenarioService {
    public int insertScenario(Scenario scenario);

    public int updateScenario(Scenario scenario);

    public int deleteScenario(int id);

    public Scenario getScenario(int id);
}