package com.swapper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.EnvironmentConfig;
import com.swapper.dto.PlanetsDTO;
import com.swapper.dto.PlanetsListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetsServiceImpl implements PlanetsService {

    @Autowired
    private HttpRequestHandler httpRequestHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Override
    public PlanetsListDTO all(int page) throws Exception {
        String url = String.format("%s/api/planets?page=%o", environmentConfig.getApiSwapiUrl(), page);
        String response = httpRequestHandler.get(url, HttpRequestHandler.GET);
        return objectMapper.readValue(response, PlanetsListDTO.class);
    }

    @Override
    public PlanetsDTO byId(int id) throws Exception {
        String url = String.format("%s/api/planets/%o", environmentConfig.getApiSwapiUrl(), id);
        String response = httpRequestHandler.get(url, HttpRequestHandler.GET);
        return objectMapper.readValue(response, PlanetsDTO.class);
    }
}