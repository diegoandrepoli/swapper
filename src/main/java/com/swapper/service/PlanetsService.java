package com.swapper.service;

import com.swapper.dto.PlanetsDTO;
import com.swapper.dto.PlanetsListDTO;

public interface PlanetsService {

    PlanetsListDTO all(int page) throws Exception;

    PlanetsDTO byId(int id) throws Exception;
}