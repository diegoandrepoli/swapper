package com.swapper.service;

import com.swapper.dto.PeopleDTO;
import com.swapper.dto.PeopleListDTO;

public interface PeopleService {

    PeopleListDTO all(int page) throws Exception;

    PeopleDTO byId(int id) throws Exception;
}