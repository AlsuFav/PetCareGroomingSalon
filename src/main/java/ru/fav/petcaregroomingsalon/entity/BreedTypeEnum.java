package ru.fav.petcaregroomingsalon.entity;

public enum BreedTypeEnum {
    SMALL ("Маленькая"),
    MEDIUM ("Средняя"),
    LARGE ("Большая");

    private final String title;

    BreedTypeEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
