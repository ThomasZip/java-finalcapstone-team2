package com.techelevator.Services;

import com.techelevator.model.Outing;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OutingOperationsService{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String OUTING_API_BASE_URL = "http://localhost:9000/api/outings";



    }



