package com.techelevator.dao;

import com.techelevator.model.YelpApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface RestaurantSearchDao {
    public YelpApiResponse getRestaurantsByZip(String zipCode);
}
