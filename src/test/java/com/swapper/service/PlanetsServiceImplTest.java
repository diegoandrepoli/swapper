package com.swapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.EnvironmentConfig;
import com.swapper.dto.PlanetsDTO;
import com.swapper.dto.PlanetsListDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PlanetsServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PlanetsServiceImplTest {

    private static final String SERVICE_URL = "http://test.dev";

    private static final String SERVICE_METHOD = "GET";

    @InjectMocks
    private PlanetsServiceImpl planetsServiceImpl;

    @Mock
    private HttpRequestHandler httpRequestHandler;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private EnvironmentConfig environmentConfig;

    private PlanetsListDTO planetsListDTO;

    private PlanetsDTO planetsDTO;

    private String planetsListDTOAsString;

    private String planetsDTOAsString;

    @BeforeAll
    public void setup() throws JsonProcessingException {
        String name = "Sorreau";
        String diameter = "5506";
        String climate = "arid";
        String terrain = "desert";

        this.planetsDTO = new PlanetsDTO();
        planetsDTO.setName(name);
        planetsDTO.setDiameter(diameter);
        planetsDTO.setClimate(climate);
        planetsDTO.setTerrain(terrain);

        this.planetsListDTO = new PlanetsListDTO();
        planetsListDTO.setCount(1);
        List<PlanetsDTO> planetsDTOList = new ArrayList<>();
        planetsDTOList.add(planetsDTO);
        planetsListDTO.setResults(planetsDTOList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.planetsListDTOAsString = ow.writeValueAsString(planetsListDTO);
        this.planetsDTOAsString = ow.writeValueAsString(planetsDTO);
    }

    @Test
    public void allTest() throws Exception {
        String url = SERVICE_URL + "/api/planets?page=1";
        int page = 1;

        when(environmentConfig.getApiSwapiUrl()).thenReturn(SERVICE_URL);
        when(httpRequestHandler.get(url, SERVICE_METHOD)).thenReturn(planetsListDTOAsString);
        when(objectMapper.readValue(planetsListDTOAsString, PlanetsListDTO.class)).thenReturn(planetsListDTO);

        PlanetsListDTO result = planetsServiceImpl.all(page);
        assertTrue(new ReflectionEquals(result).matches(planetsListDTO));
    }

    @Test
    public void byIdTest() throws Exception {
        String url = SERVICE_URL + "/api/planets/1";

        when(environmentConfig.getApiSwapiUrl()).thenReturn(SERVICE_URL);
        when(httpRequestHandler.get(url, SERVICE_METHOD)).thenReturn(planetsDTOAsString);
        when(objectMapper.readValue(planetsDTOAsString, PlanetsDTO.class)).thenReturn(planetsDTO);

        PlanetsDTO result = planetsServiceImpl.byId(1);
        assertTrue(new ReflectionEquals(result).matches(planetsDTO));
    }
}