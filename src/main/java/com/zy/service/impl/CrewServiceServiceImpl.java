package com.zy.service.impl;

import com.zy.domain.CrewService;
import com.zy.mapper.CrewServiceMapper;
import com.zy.service.ICrewServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrewServiceServiceImpl implements ICrewServiceService {
    @Autowired
    private CrewServiceMapper crewServiceMapper;

    @Override
    public int insertCrewService(CrewService crewService) {
        return crewServiceMapper.insertCrewService(crewService);
    }

    @Override
    public int updateCrewService(CrewService crewService) {
        return crewServiceMapper.updateCrewService(crewService);
    }

    @Override
    public int deleteCrewService(int id) {
        return crewServiceMapper.deleteCrewService(id);
    }

    @Override
    public CrewService getCrewService(int id) {
        return crewServiceMapper.getCrewService(id);
    }
}