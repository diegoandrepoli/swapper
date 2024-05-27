package com.swapper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentConfig {

    @Value("${api.swapi.url}")
    private String apiSwapiUrl;

    public String getApiSwapiUrl() {
        return this.apiSwapiUrl;
    }
}