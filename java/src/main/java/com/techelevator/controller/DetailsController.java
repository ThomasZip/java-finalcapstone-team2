package com.techelevator.controller;

import com.techelevator.model.BusinessDetails;
import com.techelevator.Services.BusinessDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/restaurants/")
public class DetailsController {
    private final BusinessDetailsService businessDetailsService;

    public DetailsController(BusinessDetailsService businessDetailsService) {
        this.businessDetailsService = businessDetailsService;
    }


    @GetMapping("/get-details/{businessId}")
    public BusinessDetails getBusinessDetails(@PathVariable("businessId") String businessId) {
        return businessDetailsService.getBusinessDetailsFromId(businessId);
    }

    //  @GetMapping("/get-details/4")



}


