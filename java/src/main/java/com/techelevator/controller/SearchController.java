package com.techelevator.controller;

import com.techelevator.Services.RestaurantSearchService;
import com.techelevator.model.YelpApiResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/restaurants/")
public class SearchController {

    private final RestaurantSearchService restaurantSearchService;

    public SearchController(RestaurantSearchService restaurantSearchService){
        this.restaurantSearchService = restaurantSearchService;
    }

    @RequestMapping(path = "search/{zipCode}/{category}", method = RequestMethod.GET)
    public YelpApiResponse getRestaurantsByZipPlusCategory(@PathVariable("zipCode") String zipCode, @PathVariable("category") String category){
        return restaurantSearchService.getRestaurantsByZipAndCategory(zipCode, category);
    }

    @RequestMapping(path = "search/{zipCode}", method = RequestMethod.GET)
    public YelpApiResponse getRestaurantsByZipPlusCategory(@PathVariable("zipCode") String zipCode){
        return restaurantSearchService.getRestaurantsByZipOnly(zipCode);
    }
}
