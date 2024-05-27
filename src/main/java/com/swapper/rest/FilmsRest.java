package com.swapper.rest;

import com.swapper.dto.FilmDTO;
import com.swapper.dto.FilmListDTO;
import com.swapper.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/films")
public class FilmsRest {

    @Autowired
    private FilmsService filmsService;

    @GetMapping()
    public FilmListDTO all(@RequestParam(value = "page", required = false, name = "page", defaultValue = "1") int page) throws Exception {
        return filmsService.all(page);
    }

    @GetMapping("/{id}")
    public FilmDTO byId(@PathVariable(name = "id") int id) throws Exception {
        return filmsService.byId(id);
    }
}