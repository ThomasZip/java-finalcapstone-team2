package com.techelevator.controller;

import com.techelevator.dao.RestaurantSearchDao;
import com.techelevator.model.YelpApiResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/restaurants/")
public class SearchController {

    private final RestaurantSearchDao restaurantSearchDao;

    public SearchController(RestaurantSearchDao restaurantSearchDao){
        this.restaurantSearchDao = restaurantSearchDao;
    }

    @RequestMapping(path = "search/{zipCode}/{category}", method = RequestMethod.GET)
    public YelpApiResponse getRestaurantsByZipPlusCategory(@PathVariable("zipCode") String zipCode, @PathVariable("category") String category){
        return restaurantSearchDao.getRestaurantsByZipAndCategory(zipCode, category);
    }

    @RequestMapping(path = "search/{zipCode}", method = RequestMethod.GET)
    public YelpApiResponse getRestaurantsByZipPlusCategory(@PathVariable("zipCode") String zipCode){
        return restaurantSearchDao.getRestaurantsByZipOnly(zipCode);
    }

}
