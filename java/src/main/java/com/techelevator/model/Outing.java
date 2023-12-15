package com.techelevator.model;

import java.util.List;

public class Outing {
    private int outingId;
    private String name;
    private String zipCode;
    private String dateDeadline;
    private String dateEvent;
    private int creatorId;
    private List<Guest> guests;
    private List<Restaurant> outingRestaurants;


    public Outing(int outingId, String name, String zipCode, String dateDeadline, String dateEvent, int creatorId, List<Guest> guests, List<Restaurant> outingRestaurants) {
        this.outingId = outingId;
        this.name = name;
        this.zipCode = zipCode;
        this.dateDeadline = dateDeadline;
        this.dateEvent = dateEvent;
        this.creatorId = creatorId;
        this.guests = guests;
        this.outingRestaurants = outingRestaurants;

    }
    public Outing(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDateDeadline() {
        return dateDeadline;
    }

    public void setDateDeadline(String dateDeadline) {
        this.dateDeadline = dateDeadline;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }


    public List<Restaurant> getOutingRestaurants() {
        return outingRestaurants;
    }

    public void setOutingRestaurants(List<Restaurant> outingRestaurants) {
        this.outingRestaurants = outingRestaurants;
    }

    public int getOutingId() {
        return outingId;
    }

    public void setOutingId(int outingId) {
        this.outingId = outingId;
    }

}
