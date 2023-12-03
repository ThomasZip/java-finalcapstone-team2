package com.techelevator.model;

import java.util.List;

public class YelpApiResponse {
    private List<BusinessSearchDao> businesses;

    public List<BusinessSearchDao>getBusinesses(){
        return this.businesses;
    }
    public void setBusinesses(List<BusinessSearchDao> businesses){
        this.businesses = businesses;
    }
}
