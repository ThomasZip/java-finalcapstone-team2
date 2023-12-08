package com.techelevator.model;

import java.util.List;

public class MyOutings {
    private List<OutingDto> myOutings;

    public MyOutings(List<OutingDto> myOutings) {
        this.myOutings = myOutings;
    }

    public MyOutings(){}

    public List<OutingDto> getMyOutings() {
        return myOutings;
    }

    public void setMyOutings(List<OutingDto> myOutings) {
        this.myOutings = myOutings;
    }
}
