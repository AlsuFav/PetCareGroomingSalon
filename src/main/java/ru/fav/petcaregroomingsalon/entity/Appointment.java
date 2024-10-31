package ru.fav.petcaregroomingsalon.entity;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private int id;
    private Pet pet;
    private Groomer groomer;
    private Service service;
    private int price;
    private java.sql.Timestamp date;
    private String notes;


    public Appointment() {}


    public Appointment(Pet pet, Groomer groomer, Service service, int price, java.sql.Timestamp date, String notes) {
        this.pet = pet;
        this.groomer = groomer;
        this.service = service;
        this.price = price;
        this.date = date;
        this.notes = notes;
    }

    public Appointment(int id, Pet pet, Groomer groomer,  Service service, int price, java.sql.Timestamp date, String notes) {
        this.id = id;
        this.pet = pet;
        this.groomer = groomer;
        this.service = service;
        this.price = price;
        this.date = date;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Groomer getGroomer() {
        return groomer;
    }

    public void setGroomer(Groomer groomer) {
        this.groomer = groomer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateAndTime () {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return date.toLocalDateTime().format(dateTimeFormatter);
    }
}