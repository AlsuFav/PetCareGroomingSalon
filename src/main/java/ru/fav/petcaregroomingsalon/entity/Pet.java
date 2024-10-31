package ru.fav.petcaregroomingsalon.entity;

import java.sql.Date;

public class Pet {
    private int id;
    private String name;
    private String species;
    private Breed breed;
    private java.sql.Date birthDate;
    private Client owner;

    public Pet() {}

    public Pet(String name, String species, Breed breed, java.sql.Date birthDate, Client owner) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    public Pet(int id, String name, String species, Breed breed, java.sql.Date birthDate, Client owner) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    // Getters and setters
}