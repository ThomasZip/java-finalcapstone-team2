package com.techelevator.model;

//this is a class to hold information about a restaurant's category

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    @JsonProperty("alias")
    private String alias;
    @JsonProperty("title")
    private String title;

    //empty constructor
    public Category() {

    }

    public Category(String alias, String title) {
        this.alias = alias;
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
