package com.techelevator.model;

public class Category {
    private String title;

    private String alias;

    public Category(String title) {
        this.title = title;
    }
    public Category(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


}
