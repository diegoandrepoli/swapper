package com.swapper.rest;

import com.swapper.dto.PeopleDTO;
import com.swapper.dto.PeopleListDTO;
import com.swapper.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/people")
public class PeopleRest {

    @Autowired
    private PeopleService peopleService;

    @GetMapping()
    public PeopleListDTO all(@RequestParam(value = "page", required = false, name = "page", defaultValue = "1") int page) throws Exception {
        return peopleService.all(page);
    }

    @GetMapping("/{id}")
    public PeopleDTO byId(@PathVariable(name = "id") int id) throws Exception {
        return peopleService.byId(id);
    }
}