package com.swapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.SwapiConfig;
import com.swapper.dto.PlanetsDTO;
import com.swapper.dto.PlanetsListDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PlanetsServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PlanetsServiceImplTest {

    @InjectMocks
    private PlanetsServiceImpl planetsServiceImpl;

    @Mock
    private HttpRequestHandler httpRequestHandler;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private SwapiConfig swapiConfig;

    private ObjectWriter objectWriter;

    private String serviceUrl;

    @BeforeAll
    public void setup() throws JsonProcessingException {
        this.serviceUrl = "http://test.dev";
        this.objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @Test
    public void allTest() throws Exception {
        String url = serviceUrl + "/api/planets?page=1";

        PlanetsDTO planet = new PlanetsDTO();
        planet.setName("Sorreau");
        planet.setDiameter("5506");
        planet.setClimate("arid");
        planet.setTerrain("desert");

        List<PlanetsDTO> list = new ArrayList<>();
        list.add(planet);

        PlanetsListDTO planets = new PlanetsListDTO();
        planets.setCount(1);
        planets.setResults(list);

        String planetsAsString = this.objectWriter.writeValueAsString(planets);

        when(this.swapiConfig.getUrl()).thenReturn(serviceUrl);
        when(this.httpRequestHandler.get(url, "GET")).thenReturn(planetsAsString);
        when(this.objectMapper.readValue(planetsAsString, PlanetsListDTO.class)).thenReturn(planets);

        PlanetsListDTO result = this.planetsServiceImpl.all(1);

        String resultAsString = this.objectWriter.writeValueAsString(result);
        assertEquals(resultAsString, planetsAsString);
    }

    @Test
    public void byIdTest() throws Exception {
        String url = serviceUrl + "/api/planets/1";

        PlanetsDTO planet = new PlanetsDTO();
        planet.setName("Sorreau");
        planet.setDiameter("5506");
        planet.setClimate("arid");
        planet.setTerrain("desert");

        String planetAsString = this.objectWriter.writeValueAsString(planet);

        when(this.swapiConfig.getUrl()).thenReturn(serviceUrl);
        when(this.httpRequestHandler.get(url, "GET")).thenReturn(planetAsString);
        when(this.objectMapper.readValue(planetAsString, PlanetsDTO.class)).thenReturn(planet);

        PlanetsDTO result = this.planetsServiceImpl.byId(1);

        String resultAsString = this.objectWriter.writeValueAsString(result);
        assertEquals(resultAsString, planetAsString);
    }
}