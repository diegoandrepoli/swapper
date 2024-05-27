package com.swapper.service;

import com.swapper.dto.FilmListDTO;

public interface FilmsService {

    FilmListDTO all(int page, String format) throws Exception;
}