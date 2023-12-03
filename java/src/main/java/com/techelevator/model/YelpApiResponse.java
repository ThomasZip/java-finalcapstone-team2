package com.techelevator.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class YelpApiResponse {

    //using annotation to map JSON response property, "businesses" to "restaurants" variable;
    //List of RestaurantSearchResultsDto's, called "restaurants", corresponds to the array of
    //restaurants in the JSON response
    @JsonProperty("businesses")
    private List<RestaurantSearchResultsDto> restaurants;

    //getter and setter for restaurants
    public List<RestaurantSearchResultsDto> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantSearchResultsDto> restaurants) {
        this.restaurants = restaurants;
    }
}
