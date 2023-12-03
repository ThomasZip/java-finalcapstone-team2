package com.techelevator.dao;

import com.techelevator.model.YelpApiResponse;

public interface RestaurantSearchDao {
    public YelpApiResponse getRestaurantsByZip(String zipCode);
}
