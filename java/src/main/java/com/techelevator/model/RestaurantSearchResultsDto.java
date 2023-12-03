package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RestaurantSearchResultsDto {

    @JsonProperty("id")
    private String restaurantId;
    @JsonProperty("name")
    private String restaurantName;
    @JsonProperty("image_url")
    private String image_url;
    @JsonProperty("url")
    private String restaurantURL;
    @JsonProperty("categories")
    private List<Category> categories;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("price")
    private String price;
    @JsonProperty("location")
    private Location location;

    public RestaurantSearchResultsDto() {

    };

    public RestaurantSearchResultsDto(String restaurantId, String restaurantName, String image_url, String restaurantURL, List<Category> categories, double rating, String price, Location location) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.image_url = image_url;
        this.restaurantURL = restaurantURL;
        this.categories = categories;
        this.rating = rating;
        this.price = price;
        this.location = location;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRestaurantURL() {
        return restaurantURL;
    }

    public void setRestaurantURL(String restaurantURL) {
        this.restaurantURL = restaurantURL;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public void setLocation(Location location) {
        this.location = location;
    }
}
