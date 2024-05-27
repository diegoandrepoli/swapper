package com.swapper.rest;

import com.swapper.dto.PlanetsDTO;
import com.swapper.dto.PlanetsListDTO;
import com.swapper.service.PlanetsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/planets")
@Tag(name = "Planets")
public class PlanetsRest {

    @Autowired
    private PlanetsService planetService;

    @GetMapping()
    @Operation(summary = "This endpoint get all planets from Swapi API.")
    public ResponseEntity<PlanetsListDTO> all(@RequestParam(value = "page", required = false, name = "page", defaultValue = "1") int page) throws Exception {
        try {
            PlanetsListDTO planetsListDTO = planetService.all(page);
            return ResponseEntity.ok(planetsListDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "This endpoint get planet by id from Swapi API.")
    public ResponseEntity<PlanetsDTO> byId(@PathVariable(name = "id") int id) throws Exception {
        try {
            PlanetsDTO planetsDTO = planetService.byId(id);
            return ResponseEntity.ok(planetsDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}