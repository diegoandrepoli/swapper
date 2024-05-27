package com.swapper.rest;

import com.swapper.dto.FilmDTO;
import com.swapper.dto.FilmListDTO;
import com.swapper.service.FilmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/films")
@Tag(name = "Films")
public class FilmsRest {

    @Autowired
    private FilmsService filmsService;

    @GetMapping()
    @Operation(summary = "This endpoint get all films from Swapi API.")
    public FilmListDTO all(@RequestParam(value = "page", required = false, name = "page", defaultValue = "1") int page) throws Exception {
        return filmsService.all(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "This endpoint get film by id from Swapi API.")
    public FilmDTO byId(@PathVariable(name = "id") int id) throws Exception {
        return filmsService.byId(id);
    }
}