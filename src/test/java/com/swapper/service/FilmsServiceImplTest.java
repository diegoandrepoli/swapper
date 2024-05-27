package com.swapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.EnvironmentConfig;
import com.swapper.dto.FilmDTO;
import com.swapper.dto.FilmListDTO;
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

@ContextConfiguration(classes = {FilmsServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FilmsServiceImplTest {

    private static final String SERVICE_URL = "http://test.dev";

    private static final String SERVICE_METHOD = "GET";

    @InjectMocks
    private FilmsServiceImpl filmsServiceImpl;

    @Mock
    private HttpRequestHandler httpRequestHandler;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private EnvironmentConfig environmentConfig;

    private FilmListDTO filmListDTO;

    private String filmListDTOAsString;

    @BeforeAll
    public void setup() throws JsonProcessingException {
        String title = "A title";
        int episode = 1;
        String openingCrawl = "It is a period of spaceships....";
        String director = "Other";
        String producer = "Gariel";
        String url = "/api/films/1/";

        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle(title);
        filmDTO.setEpisodeId(episode);
        filmDTO.setOpeningCrawl(openingCrawl);
        filmDTO.setDirector(director);
        filmDTO.setProducer(producer);
        filmDTO.setUrl(url);

        this.filmListDTO = new FilmListDTO();
        filmListDTO.setCount(1);
        List<FilmDTO> filmDTOList = new ArrayList<>();
        filmDTOList.add(filmDTO);
        filmListDTO.setResults(filmDTOList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.filmListDTOAsString = ow.writeValueAsString(filmListDTO);
    }

    @Test
    public void allTest() throws Exception {
        String url = SERVICE_URL + "/api/films?page=1&format=json";
        String format = "json";
        int page = 1;

        when(environmentConfig.getApiSwapiUrl()).thenReturn(SERVICE_URL);
        when(httpRequestHandler.get(url, SERVICE_METHOD)).thenReturn(filmListDTOAsString);
        when(objectMapper.readValue(filmListDTOAsString, FilmListDTO.class)).thenReturn(filmListDTO);

        FilmListDTO result = filmsServiceImpl.all(page, format);
        assertTrue(new ReflectionEquals(result).matches(filmListDTO));
    }
}
