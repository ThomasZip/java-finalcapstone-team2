package com.techelevator.model;

public class Restaurant {
    private int restaurantId;
    private String name;
    private int thumbsUp;
    private int thumbsDown;
    private String longRestaurantId;

    public Restaurant(){}

    public Restaurant(int id, String name, int up, int down, String longRestaurantId){
        this.restaurantId = id;
        this.name = name;
        this.thumbsUp = up;
        this.thumbsDown = down;
        this.longRestaurantId = longRestaurantId;
    }
    public String getRestaurantName() {
        return name;
    }

    public void setRestaurantName(String restaurantName) {
        this.name = restaurantName;
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

    public String getLongRestaurantId() {
        return longRestaurantId;
    }

    public void setLongRestaurantId(String longRestaurantId) {
        this.longRestaurantId = longRestaurantId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }


}
