package ru.fav.petcaregroomingsalon.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSlot {
    private int id;
    private Groomer groomer;
    private java.sql.Timestamp startTime;
    private java.sql.Timestamp endTime;
    private boolean taken;

    public TimeSlot() {}

    public TimeSlot(Groomer groomer, java.sql.Timestamp startTime, java.sql.Timestamp endTime, boolean taken) {
        this.groomer = groomer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taken = taken;
    }

    public TimeSlot(int id, Groomer groomer, java.sql.Timestamp startTime, java.sql.Timestamp endTime, boolean taken) {
        this.id = id;
        this.groomer = groomer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taken = taken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Groomer getGroomer() {
        return groomer;
    }

    public void setGroomer(Groomer groomer) {
        this.groomer = groomer;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public String getDateAndTime () {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return startTime.toLocalDateTime().format(dateTimeFormatter);
    }

    public String getTime () {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return startTime.toLocalDateTime().format(dateTimeFormatter);
    }
}
