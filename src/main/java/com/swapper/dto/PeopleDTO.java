package com.swapper.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class PeopleDTO {

    @JsonAlias("name")
    @JsonProperty("nome")
    private String name;

    @JsonAlias("height")
    @JsonProperty("altura")
    private String height;

    @JsonAlias("mass")
    @JsonProperty("peso")
    private String mass;

    @JsonAlias("hair_color")
    @JsonProperty("cor_cableo")
    private String hairColor;

    @JsonAlias("skin_color")
    @JsonProperty("cor_pelo")
    private String skinColor;

    @JsonAlias("eye_color")
    @JsonProperty("cor_olho")
    private String eyeColor;

    @JsonAlias("birth_year")
    @JsonProperty("data_nascimento")
    private String birthYear;

    @JsonAlias("gender")
    @JsonProperty("genero")
    private String gender;

    @JsonAlias("homeworld")
    @JsonProperty("planeta_natal")
    private String homeworld;

    @JsonAlias("films")
    @JsonProperty("filmes")
    private List<String> films;

    @JsonAlias("species")
    @JsonProperty("especies")
    private List<String> species;

    @JsonAlias("vehicles")
    @JsonProperty("veiculos")
    private List<String> vehicles;

    @JsonAlias("starships")
    @JsonProperty("naves_estelares")
    private List<String> starships;

    @JsonAlias("created")
    @JsonProperty("criado")
    private Timestamp created;

    @JsonAlias("edited")
    @JsonProperty("editado")
    private Timestamp edited;

    @JsonAlias("url")
    @JsonProperty("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
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