package com.techelevator.dao;

import com.techelevator.model.YelpApiResponse;

public interface RestaurantSearchResultsDao {
    public YelpApiResponse listRestaurants(String zipcodeInput);
}
