package com.techelevator.controller;

import com.techelevator.dao.RestaurantSearchDao;
import com.techelevator.model.YelpApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/restaurants/")
@CrossOrigin(origins = "http://127.0.0.1:5174")
public class SearchController {

    private final RestaurantSearchDao restaurantSearchDao;

    public SearchController(RestaurantSearchDao restaurantSearchDao){
        this.restaurantSearchDao = restaurantSearchDao;
    }

    @RequestMapping(path = "search/{zipCode}", method = RequestMethod.GET)
    public YelpApiResponse getRestaurantsByZip(@PathVariable("zipCode") String zipCode){
        return restaurantSearchDao.getRestaurantsByZip(zipCode);
    }

}
