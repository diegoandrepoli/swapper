package com.swapper.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PeopleListDTO {

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
    private List<PeopleDTO> results;

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

    public List<PeopleDTO> getResults() {
        return results;
    }

    public void setResults(List<PeopleDTO> results) {
        this.results = results;
    }
}