package com.techelevator;

import com.techelevator.Services.BusinessSearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        BusinessSearchService businessSearchService = new BusinessSearchService();
        businessSearchService.getRestaurantsByZip("44103");
//        SpringApplication.run(Application.class, args);
    }

}
