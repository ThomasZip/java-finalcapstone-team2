package com.techelevator.services;

import com.techelevator.model.YelpApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class BusinessSearchService implements RestaurantSearchService {
    private static final String YELP_API_BASE_URL = "https://api.yelp.com/v3/businesses/search?location=";
    private static final String YELP_API_CATEGORY_URL = "%20&categories=";
    private static final String YELP_SEARCH_LIMIT_URL = "&sort_by=best_match&limit=";
    private static final String YELP_SEARCH_LIMIT_NUM = "20";
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String TOKEN = "IafMbuyJengOALTUJX8-T89A9lPKijBVAHDkxuBbbjf8_UeiHNfcmkeIC5luhcw2hPTfGqisk_1EwmaRa6umUS84Ud47KyYt23NLUiYh8OQdeuqtizxruzwILO5pZXYx";


    @Override
    public YelpApiResponse getRestaurantsByZipAndCategory(String zipCode, String category){
        YelpApiResponse yelpApiResponse = new YelpApiResponse();


        try{
            ResponseEntity<YelpApiResponse> response = restTemplate.exchange(YELP_API_BASE_URL + zipCode + YELP_API_CATEGORY_URL + category + YELP_SEARCH_LIMIT_URL + YELP_SEARCH_LIMIT_NUM,
                    HttpMethod.GET,
                    makeAuthEntity(),
                    YelpApiResponse.class);
            yelpApiResponse = response.getBody();


        }catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return yelpApiResponse;
    }

    @Override
    public YelpApiResponse getRestaurantsByZipOnly(String zipCode){
        YelpApiResponse yelpApiResponse = new YelpApiResponse();


        try{
            ResponseEntity<YelpApiResponse> response = restTemplate.exchange(YELP_API_BASE_URL + zipCode + YELP_SEARCH_LIMIT_URL + YELP_SEARCH_LIMIT_NUM,
                    HttpMethod.GET,
                    makeAuthEntity(),
                    YelpApiResponse.class);
            yelpApiResponse = response.getBody();


        }catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return yelpApiResponse;
    }



    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(headers);
    }
}
