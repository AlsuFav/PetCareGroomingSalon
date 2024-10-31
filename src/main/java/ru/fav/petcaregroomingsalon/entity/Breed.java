package ru.fav.petcaregroomingsalon.entity;

public class Breed {
    private int id;
    private String name;
    private BreedTypeEnum breedType;

    public Breed() {}

    public Breed(String name, BreedTypeEnum breedType) {
        this.name = name;
        this.breedType = breedType;
    }

    public Breed(int id, String name, BreedTypeEnum breedType) {
        this.id = id;
        this.name = name;
        this.breedType = breedType;
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

    public BreedTypeEnum getBreedType() {
        return breedType;
    }

    public void setBreedType(BreedTypeEnum breedType) {
        this.breedType = breedType;
    }

    // Getters and setters
}