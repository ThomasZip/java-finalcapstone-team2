package com.techelevator.model;

public class Restaurant {

    private int restaurantId;
    private String restaurantName;
    private int thumbsUp;
    private int thumbsDown;

    public Restaurant(){}

    public Restaurant(int id, String name, int up, int down){
        this.restaurantId = id;
        this.restaurantName = name;
        this.thumbsUp = up;
        this.thumbsDown = down;
    }
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(int thumbsDown) {
        this.thumbsDown = thumbsDown;
    }


}
