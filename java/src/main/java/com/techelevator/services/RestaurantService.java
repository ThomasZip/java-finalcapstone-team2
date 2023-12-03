package com.techelevator.services;

import com.techelevator.dao.RestaurantSearchResultsDao;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import com.techelevator.model.*;
import java.util.*;


//this service defines the methods that can be called upon in regards to restaurants
@Component
public class RestaurantService implements RestaurantSearchResultsDao {

    //setting a base url for the API
    private static final String API_BASE_URL = "https://api.yelp.com/v3/businesses/search?location=";

    //setting a manual limit on the number of restaurants returned from the search
    private static final String LIMIT_20 = "&sort_by=best_match&limit=20";

    private final RestTemplate restTemplate = new RestTemplate();

    //manually setting the yelp API authorization header
    //TODO: add the authorization header to a text file and then add a scanner to read that so the token isn't visible in the code
    private String yelpApiAuthHeader = "TdyebAsf0Nmsu6d6SvewUlw-3m4RVxhqc7XKUZCMnFY2IlQN5G_hZaHojL4FXI2JDdRahUm0brFOqwD2_c-SfBtjLNVHkOuknUEweIcjIaJy910hGP4nLQj-8EFqZXYx";


    //get the restaurant information from the yelp fusion api into a list of RestaurantSearchResultsDto Objects
    @Override
    public YelpApiResponse listRestaurants(String zipcodeInput) {

        //initialize the YelpApiResponse object, yelpApiResponse"
        YelpApiResponse yelpApiResponse = new YelpApiResponse();

        try {
            //send the get request and get back a JSON response with a datatype of YelpApiResponse
            //this includes the zipcodeInput from the path variable and the manual limit of 20 restaurants
            ResponseEntity<YelpApiResponse> response = restTemplate.exchange(API_BASE_URL + zipcodeInput + LIMIT_20, HttpMethod.GET, makeAuthEntity(), YelpApiResponse.class);
            //parse out the body of the JSON response (the array of restaurants) and set it to the new YelpApiResponse instance
            yelpApiResponse = response.getBody();

        //handle exceptions
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        //return the response, which comes from the YelpAPI
        return yelpApiResponse;
    }

    //create an HttpEntity with the 'Authorization:Bearer:' header
    private HttpEntity<Void>makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(yelpApiAuthHeader);
        return new HttpEntity<>(headers);
    }
}
