package com.techelevator.Services;

import com.techelevator.model.BusinessDetails;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class BusinessDetailsService {
    private static final String YELP_API_BASE_URL = "https://api.yelp.com/v3/businesses/";
   private final RestTemplate restTemplate = new RestTemplate();

    private static final String TOKEN = "SsEIu6BdgTyTYdfwMxxNueZ0sQCAJO7BvV0DIM3G9Ol2sFMq5A7qfvWYgkJUW_ifnqalcZYZwXG7oNOfa2alQM4ULb-a-KIXO4zvaAuRIXYRSWcUHo91oJb5lRVqZXYx";



    public BusinessDetails getBusinessDetailsFromId(String businessId) {
        String fullUrl = YELP_API_BASE_URL + businessId;

        RequestEntity<?> requestEntity = RequestEntity.get(URI.create(fullUrl))
                .header("Authorization", "Bearer " + TOKEN)
                .build();

        ResponseEntity<BusinessDetails> response = restTemplate.exchange(requestEntity, BusinessDetails.class);
        return response.getBody();
    }
}
