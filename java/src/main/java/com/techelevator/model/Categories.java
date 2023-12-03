package com.techelevator.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

//this is a class to hold a List of Category objects (pairs of alias + title)
public class Categories {

    //set the "categories" property to the same-name variable List
    @JsonProperty("categories")
    private List<Category> categories;

    //getter and setter for categories
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
