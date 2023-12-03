package com.techelevator;

import com.techelevator.services.RestaurantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        RestaurantService restaurantService = new RestaurantService();

        SpringApplication.run(Application.class, args);
    }

}
