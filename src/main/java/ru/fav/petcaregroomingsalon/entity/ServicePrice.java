package ru.fav.petcaregroomingsalon.entity;

public class ServicePrice {
    private int id;
    private Service service;
    private String species;
    private BreedTypeEnum breedType;
    private int price;


    public ServicePrice() {}


    public ServicePrice(Service service, String species, BreedTypeEnum breedType, int price) {
        this.service = service;
        this.species = species;
        this.breedType = breedType;
        this.price = price;
    }


    public ServicePrice(int id, Service service, String species, BreedTypeEnum breedType, int price) {
        this.id = id;
        this.service = service;
        this.species = species;
        this.breedType = breedType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public BreedTypeEnum getBreedType() {
        return breedType;
    }

    public void setBreedType(BreedTypeEnum breedType) {
        this.breedType = breedType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}