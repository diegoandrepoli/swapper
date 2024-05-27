package com.swapper.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class PlanetsDTO {

    @JsonAlias("name")
    @JsonProperty("nome")
    private String name;

    @JsonAlias("rotation_period")
    @JsonProperty("periodo_rotacao")
    private String rotationPeriod;

    @JsonAlias("orbital_period")
    @JsonProperty("periodo_orbita")
    private String orbitalPeriod;

    @JsonAlias("diameter")
    @JsonProperty("diametro")
    private String diameter;

    @JsonAlias("climate")
    @JsonProperty("clima")
    private String climate;

    @JsonAlias("gravity")
    @JsonProperty("gravidade")
    private String gravity;

    @JsonAlias("terrain")
    @JsonProperty("terreno")
    private String terrain;

    @JsonAlias("surface_water")
    @JsonProperty("superfície_agua")
    private String surfaceWater;

    @JsonAlias("population")
    @JsonProperty("populacao")
    private String population;

    @JsonAlias("residents")
    @JsonProperty("moradores")
    private List<String> residents;

    @JsonAlias("films")
    @JsonProperty("filmes")
    private List<String> films;

    @JsonAlias("created")
    @JsonProperty("criado")
    private Timestamp created;

    @JsonAlias("edited")
    @JsonProperty("editado")
    private Timestamp edited;

    @JsonAlias("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(String rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(String orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
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