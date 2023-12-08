package com.techelevator.model;

import java.util.List;

public class OutingDto {
    private String name;
    private String zipCode;
    private String dateDeadline;
    private String dateEvent;
    private String creator;
    private List<Guest> guests;
    private List<BusinessSearchDao> outingRestaurants;
    private String id;

    public OutingDto(String name, String zipCode, String dateDeadline, String dateEvent, String creator, List<Guest> guests, List<BusinessSearchDao> outingRestaurants, String id) {
        this.name = name;
        this.zipCode = zipCode;
        this.dateDeadline = dateDeadline;
        this.dateEvent = dateEvent;
        this.creator = creator;
        this.guests = guests;
        this.outingRestaurants = outingRestaurants;
        this.id = id;
    }
    public OutingDto(){}

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BusinessSearchDao> getOutingRestaurants() {
        return outingRestaurants;
    }

    public void setOutingRestaurants(List<BusinessSearchDao> outingRestaurants) {
        this.outingRestaurants = outingRestaurants;
    }

}
