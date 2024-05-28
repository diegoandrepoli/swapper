package com.swapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.SwapiConfig;
import com.swapper.dto.PeopleDTO;
import com.swapper.dto.PeopleListDTO;
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

@ContextConfiguration(classes = {PeopleServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PeopleServiceImplTest {

    @InjectMocks
    private PeopleServiceImpl peopleServiceImpl;

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
        String url = serviceUrl + "/api/people?page=1";

        PeopleDTO people = new PeopleDTO();
        people.setName("Arnaldo");
        people.setHeight("150");
        people.setMass("75");
        people.setGender("male");

        List<PeopleDTO> list = new ArrayList<>();
        list.add(people);

        PeopleListDTO peoples = new PeopleListDTO();
        peoples.setCount(1);
        peoples.setResults(list);

        String peoplesAsString = this.objectWriter.writeValueAsString(peoples);

        when(this.swapiConfig.getUrl()).thenReturn(serviceUrl);
        when(this.httpRequestHandler.get(url, "GET")).thenReturn(peoplesAsString);
        when(this.objectMapper.readValue(peoplesAsString, PeopleListDTO.class)).thenReturn(peoples);

        PeopleListDTO result = this.peopleServiceImpl.all(1);

        String resultAsString = this.objectWriter.writeValueAsString(result);
        assertEquals(resultAsString, peoplesAsString);
    }

    @Test
    public void byIdTest() throws Exception {
        String url = serviceUrl + "/api/people/1";

        PeopleDTO people = new PeopleDTO();
        people.setName("Arnaldo");
        people.setHeight("150");
        people.setMass("75");
        people.setGender("male");

        String peopleAsString = this.objectWriter.writeValueAsString(people);

        when(this.swapiConfig.getUrl()).thenReturn(serviceUrl);
        when(this.httpRequestHandler.get(url, "GET")).thenReturn(peopleAsString);
        when(this.objectMapper.readValue(peopleAsString, PeopleDTO.class)).thenReturn(people);

        PeopleDTO result = this.peopleServiceImpl.byId(1);

        String resultAsString = this.objectWriter.writeValueAsString(result);
        assertEquals(resultAsString, peopleAsString);
    }
}