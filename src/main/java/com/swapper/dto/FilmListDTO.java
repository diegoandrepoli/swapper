package com.swapper.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FilmListDTO {

    @JsonAlias("count")
    @JsonProperty("total")
    private int count;

    @JsonAlias("next")
    @JsonProperty("proximo")
    private String next;

    @JsonAlias("previous")
    @JsonProperty("anterior")
    private String previous;

    @JsonAlias("results")
    @JsonProperty("resultados")
    private List<FilmDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<FilmDTO> getResults() {
        return results;
    }

    public void setResults(List<FilmDTO> results) {
        this.results = results;
    }
}