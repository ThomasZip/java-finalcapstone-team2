package com.techelevator.Services;

import com.techelevator.model.YelpApiResponse;

public interface RestaurantSearchService {
    YelpApiResponse getRestaurantsByZipAndCategory(String zipCode, String category);

    YelpApiResponse getRestaurantsByZipOnly(String zipCode);
}

