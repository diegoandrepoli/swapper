package com.swapper.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class FilmDTO {

    @JsonAlias("title")
    @JsonProperty("titulo")
    private String title;

    @JsonAlias("episode_id")
    @JsonProperty("id_episodio")
    private int episodeId;

    @JsonAlias("opening_crawl")
    @JsonProperty("rastreamento_abertura")
    private String openingCrawl;

    @JsonAlias("director")
    @JsonProperty("diretor")
    private String director;

    @JsonAlias("producer")
    @JsonProperty("produtor")
    private String producer;

    @JsonAlias("release_date")
    @JsonProperty("data_lancamento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @JsonAlias("characters")
    @JsonProperty("personagens")
    private List<String> characters;

    @JsonAlias("planets")
    @JsonProperty("planetas")
    private List<String> planets;

    @JsonAlias("starships")
    @JsonProperty("naves_estelares")
    private List<String> starships;

    @JsonAlias("vehicles")
    @JsonProperty("veiculos")
    private List<String> vehicles;

    @JsonAlias("species")
    @JsonProperty("especies")
    private List<String> species;

    @JsonAlias("created")
    @JsonProperty("criado")
    private Timestamp created;

    @JsonAlias("edited")
    @JsonProperty("editado")
    private Timestamp edited;

    @JsonAlias("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getEdited() {
        return edited;
    }

    public void setEdited(Timestamp edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}