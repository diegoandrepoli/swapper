package com.swapper.service;

import com.swapper.dto.FilmDTO;
import com.swapper.dto.FilmListDTO;

public interface FilmsService {

    FilmListDTO all(int page) throws Exception;

    FilmDTO byId(int id) throws Exception;
}