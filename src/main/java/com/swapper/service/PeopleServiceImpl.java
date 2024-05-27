package com.swapper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.EnvironmentConfig;
import com.swapper.dto.PeopleDTO;
import com.swapper.dto.PeopleListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private HttpRequestHandler httpRequestHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Override
    public PeopleListDTO all(int page) throws Exception {
        String url = String.format("%s/api/people?page=%o", environmentConfig.getApiSwapiUrl(), page);
        String response = httpRequestHandler.get(url, HttpRequestHandler.GET);
        return objectMapper.readValue(response, PeopleListDTO.class);
    }

    @Override
    public PeopleDTO byId(int id) throws Exception {
        String url = String.format("%s/api/people/%o", environmentConfig.getApiSwapiUrl(), id);
        String response = httpRequestHandler.get(url, HttpRequestHandler.GET);
        return objectMapper.readValue(response, PeopleDTO.class);
    }
}