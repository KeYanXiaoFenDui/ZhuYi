package com.zy.service;

import com.zy.domain.CrewService;
import org.springframework.stereotype.Service;

@Service
public interface ICrewServiceService {
    public int insertCrewService(CrewService crewService);

    public int updateCrewService(CrewService crewService);

    public int deleteCrewService(int id);

    public CrewService getCrewService(int id);
}