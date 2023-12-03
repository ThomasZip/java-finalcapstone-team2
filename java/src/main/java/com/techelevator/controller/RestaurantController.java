package com.techelevator.controller;

import com.techelevator.dao.RestaurantSearchResultsDao;
import com.techelevator.model.YelpApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/restaurants/")
public class RestaurantController {

    private final RestaurantSearchResultsDao restaurantSearchResultsDao;

    public RestaurantController(RestaurantSearchResultsDao restaurantSearchResultsDao) {
        this.restaurantSearchResultsDao = restaurantSearchResultsDao;
    }

    @RequestMapping(path = "search/{zipcodeInput}", method = RequestMethod.GET)
    public YelpApiResponse listRestaurants(@PathVariable("zipcodeInput") String zipcodeInput) {
        return restaurantSearchResultsDao.listRestaurants(zipcodeInput);
    }
}
