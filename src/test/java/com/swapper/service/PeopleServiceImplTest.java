package com.swapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.EnvironmentConfig;
import com.swapper.dto.PeopleDTO;
import com.swapper.dto.PeopleListDTO;
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

@ContextConfiguration(classes = {PeopleServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PeopleServiceImplTest {

    private static final String SERVICE_URL = "http://test.dev";

    private static final String SERVICE_METHOD = "GET";

    @InjectMocks
    private PeopleServiceImpl peopleServiceImpl;

    @Mock
    private HttpRequestHandler httpRequestHandler;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private EnvironmentConfig environmentConfig;

    private PeopleListDTO peopleListDTO;

    private PeopleDTO peopleDTO;

    private String peopleListDTOAsString;

    private String peopleDTOAsString;

    @BeforeAll
    public void setup() throws JsonProcessingException {
        String name = "Arnaldo";
        String height = "150";
        String mass = "75";
        String gender = "male";

        this.peopleDTO = new PeopleDTO();
        peopleDTO.setName(name);
        peopleDTO.setHeight(height);
        peopleDTO.setMass(mass);
        peopleDTO.setGender(gender);

        this.peopleListDTO = new PeopleListDTO();
        peopleListDTO.setCount(1);
        List<PeopleDTO> peopleDTOList = new ArrayList<>();
        peopleDTOList.add(peopleDTO);
        peopleListDTO.setResults(peopleDTOList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.peopleListDTOAsString = ow.writeValueAsString(peopleListDTO);
        this.peopleDTOAsString = ow.writeValueAsString(peopleDTO);
    }

    @Test
    public void allTest() throws Exception {
        String url = SERVICE_URL + "/api/people?page=1";
        int page = 1;

        when(environmentConfig.getApiSwapiUrl()).thenReturn(SERVICE_URL);
        when(httpRequestHandler.get(url, SERVICE_METHOD)).thenReturn(peopleListDTOAsString);
        when(objectMapper.readValue(peopleListDTOAsString, PeopleListDTO.class)).thenReturn(peopleListDTO);

        PeopleListDTO result = peopleServiceImpl.all(page);
        assertTrue(new ReflectionEquals(result).matches(peopleListDTO));
    }

    @Test
    public void byIdTest() throws Exception {
        String url = SERVICE_URL + "/api/people/1";

        when(environmentConfig.getApiSwapiUrl()).thenReturn(SERVICE_URL);
        when(httpRequestHandler.get(url, SERVICE_METHOD)).thenReturn(peopleDTOAsString);
        when(objectMapper.readValue(peopleDTOAsString, PeopleDTO.class)).thenReturn(peopleDTO);

        PeopleDTO result = peopleServiceImpl.byId(1);
        assertTrue(new ReflectionEquals(result).matches(peopleDTO));
    }
}