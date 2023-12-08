package com.techelevator.Services;

import com.techelevator.model.OutingDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OutingOperationsService implements OutingService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String OUTING_API_BASE_URL = "http://localhost:9000/api/outings";

    @Override
    public void addOuting(OutingDto outingDto){

    }


}
