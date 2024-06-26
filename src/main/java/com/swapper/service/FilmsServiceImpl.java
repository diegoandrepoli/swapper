package com.swapper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.SwapiConfig;
import com.swapper.dto.FilmDTO;
import com.swapper.dto.FilmListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmsServiceImpl implements FilmsService {

    @Autowired
    private HttpRequestHandler httpRequestHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SwapiConfig swapiConfig;

    @Override
    public FilmListDTO all(int page) throws Exception {
        String url = String.format("%s/api/films?page=%o", swapiConfig.getUrl(), page);
        String response = httpRequestHandler.get(url, HttpRequestHandler.GET);
        return objectMapper.readValue(response, FilmListDTO.class);
    }

    @Override
    public FilmDTO byId(int id) throws Exception {
        String url = String.format("%s/api/films/%o", swapiConfig.getUrl(), id);
        String response = httpRequestHandler.get(url, HttpRequestHandler.GET);
        return objectMapper.readValue(response, FilmDTO.class);
    }
}