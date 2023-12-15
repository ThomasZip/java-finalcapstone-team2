package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
@JsonInclude
public class BusinessDetails {
    private String name;



    @JsonProperty("date_opened")
    private String dateOpened;
    private String phone;
    @JsonProperty("review_count")
    private int reviewCount;
    private List<String> photos;
    private String price;
    private List<BusinessHours> hours;

    //New ones:
    private List<Category> categories;
//    private Category category;
    private String rating;
    private Location location;
    @JsonProperty("id")
    private String business_id;



    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }




    public String getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(String dateOpened) {
        this.dateOpened = dateOpened;
    }

    private List<String> transactions;
    private List<SpecialHours> specialHours;

    public List<SpecialHours> getSpecialHours() {
        return specialHours;
    }

    public void setSpecialHours(List<SpecialHours> specialHours) {
        this.specialHours = specialHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<BusinessHours> getHours() {
        return hours;
    }

    public void setHours(List<BusinessHours> hours) {
        this.hours = hours;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }



}