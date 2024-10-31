package ru.fav.petcaregroomingsalon.entity;

import java.sql.Date;

public class Groomer {
    private int id;
    private String firstName;
    private String lastName;
    private java.sql.Date careerStart;
    private String email;
    private String phone;

    public Groomer() {}

    public Groomer(String firstName, String lastName, java.sql.Date careerStart, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.careerStart = careerStart;
        this.email = email;
        this.phone = phone;
    }

    public Groomer(int id, String firstName, String lastName, java.sql.Date careerStart, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.careerStart = careerStart;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCareerStart() {
        return careerStart;
    }

    public void setCareerStart(Date careerStart) {
        this.careerStart = careerStart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getters and setters
}