package com.zy.service.impl;

import com.zy.domain.Scenario;
import com.zy.mapper.ScenarioMapper;
import com.zy.service.IScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScenarioServiceImpl implements IScenarioService {
    @Autowired
    private ScenarioMapper scenarioMapper;

    @Override
    public int insertScenario(Scenario scenario) {
        return scenarioMapper.insertScenario(scenario);
    }

    @Override
    public int updateScenario(Scenario scenario) {
        return scenarioMapper.updateScenario(scenario);
    }

    @Override
    public int deleteScenario(int id) {
        return scenarioMapper.deleteScenario(id);
    }

    @Override
    public Scenario getScenario(int id) {
        return scenarioMapper.getScenario(id);
    }
}