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

    private static final String TOKEN = "TdyebAsf0Nmsu6d6SvewUlw-3m4RVxhqc7XKUZCMnFY2IlQN5G_hZaHojL4FXI2JDdRahUm0brFOqwD2_c-SfBtjLNVHkOuknUEweIcjIaJy910hGP4nLQj-8EFqZXYx";



    public BusinessDetails getBusinessDetailsFromId(String businessId) {
        String fullUrl = YELP_API_BASE_URL + businessId;

        RequestEntity<?> requestEntity = RequestEntity.get(URI.create(fullUrl))
                .header("Authorization", "Bearer " + TOKEN)
                .build();

        ResponseEntity<BusinessDetails> response = restTemplate.exchange(requestEntity, BusinessDetails.class);
        return response.getBody();
    }
}
