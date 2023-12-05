package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BusinessSearchDao {


    private String id;
    private String name;
    @JsonProperty("image_url")
    private String imgUrl;
    private List<Category> categories;
    private double rating;
    @JsonProperty("transactions")
    private List<String> transactionTypes;
    private String price;
    private Location location;
    @JsonProperty("display_phone")
    private String displayPhone;
    private Coordinates coordinates;

    private Coordinates coordinates;

    public BusinessSearchDao(String id, String name, String imgUrl,List <Category> categories, double rating, List<String> transactionTypes, String price, Location address, String displayPhone, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.categories = categories;
        this.rating = rating;
        this.transactionTypes = transactionTypes;
        this.price = price;
        this.location = address;
        this.displayPhone = displayPhone;
        this.coordinates = coordinates;
    }
    public BusinessSearchDao(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getTransactionTypes() {
        return transactionTypes;
    }

    public void setTransactionTypes(List<String> transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location address) {
        this.location = address;
    }

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
