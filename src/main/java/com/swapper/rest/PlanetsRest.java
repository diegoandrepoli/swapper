package com.swapper.rest;

import com.swapper.dto.PlanetsDTO;
import com.swapper.dto.PlanetsListDTO;
import com.swapper.service.PlanetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/planets")
public class PlanetsRest {

    @Autowired
    private PlanetsService planetService;

    @GetMapping()
    public PlanetsListDTO all(@RequestParam(value = "page", required = false, name = "page", defaultValue = "1") int page) throws Exception {
        return planetService.all(page);
    }

    @GetMapping("/{id}")
    public PlanetsDTO byId(@PathVariable(name = "id") int id) throws Exception {
        return planetService.byId(id);
    }
}