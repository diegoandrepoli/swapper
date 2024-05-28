package com.swapper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swapper.component.HttpRequestHandler;
import com.swapper.config.SwapiConfig;
import com.swapper.dto.FilmDTO;
import com.swapper.dto.FilmListDTO;
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

@ContextConfiguration(classes = {FilmsServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FilmsServiceImplTest {

    @InjectMocks
    private FilmsServiceImpl filmsServiceImpl;

    @Mock
    private HttpRequestHandler httpRequestHandler;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private SwapiConfig swapiConfig;

    private ObjectWriter objectWriter;

    private String serviceUrl;

    @BeforeAll
    public void setup() {
        this.serviceUrl = "http://test.dev";
        this.objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @Test
    public void allTest() throws Exception {
        String url = serviceUrl + "/api/films?page=1";

        FilmDTO film = new FilmDTO();
        film.setTitle("Super film");
        film.setEpisodeId(1);
        film.setOpeningCrawl("It is a period of spaceships....");
        film.setDirector("Joseph");
        film.setProducer("Maiam");
        film.setUrl("/api/films/1");

        List<FilmDTO> list = new ArrayList<>();
        list.add(film);

        FilmListDTO films = new FilmListDTO();
        films.setCount(1);
        films.setResults(list);

        String filsmAsString = this.objectWriter.writeValueAsString(films);

        when(this.swapiConfig.getUrl()).thenReturn(serviceUrl);
        when(this.httpRequestHandler.get(url, "GET")).thenReturn(filsmAsString);
        when(this.objectMapper.readValue(filsmAsString, FilmListDTO.class)).thenReturn(films);

        FilmListDTO result = filmsServiceImpl.all(1);

        String resultAsString = this.objectWriter.writeValueAsString(result);
        assertEquals(resultAsString, filsmAsString);
    }

    @Test
    public void byIdTest() throws Exception {
        String url = serviceUrl + "/api/films/1";

        FilmDTO film = new FilmDTO();
        film.setTitle("Super film");
        film.setEpisodeId(1);
        film.setOpeningCrawl("It is a period of spaceships....");
        film.setDirector("Joseph");
        film.setProducer("Maiam");
        film.setUrl("/api/films/1");

        String filmAsString = this.objectWriter.writeValueAsString(film);

        when(this.swapiConfig.getUrl()).thenReturn(serviceUrl);
        when(this.httpRequestHandler.get(url, "GET")).thenReturn(filmAsString);
        when(this.objectMapper.readValue(filmAsString, FilmDTO.class)).thenReturn(film);

        FilmDTO result = filmsServiceImpl.byId(1);

        String resultAsString = this.objectWriter.writeValueAsString(result);
        assertEquals(resultAsString, filmAsString);
    }
}
