package com.swapper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwapiConfig {

    @Value("${api.swapi.url}")
    private String url;

    public String getUrl() {
        return this.url;
    }
}