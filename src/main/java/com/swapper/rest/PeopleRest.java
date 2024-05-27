package com.swapper.rest;

import com.swapper.dto.PeopleDTO;
import com.swapper.dto.PeopleListDTO;
import com.swapper.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/people")
@Tag(name = "People")
public class PeopleRest {

    @Autowired
    private PeopleService peopleService;

    @GetMapping()
    @Operation(summary = "This endpoint get all peoples from Swapi API.")
    public ResponseEntity<PeopleListDTO> all(@RequestParam(value = "page", required = false, name = "page", defaultValue = "1") int page) throws Exception {
        try {
            PeopleListDTO peopleListDTO = peopleService.all(page);
            return ResponseEntity.ok(peopleListDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "This endpoint get people by id from Swapi API.")
    public ResponseEntity<PeopleDTO> byId(@PathVariable(name = "id") int id) throws Exception {
        try {
            PeopleDTO peopleDTO = peopleService.byId(id);
            return ResponseEntity.ok(peopleDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}