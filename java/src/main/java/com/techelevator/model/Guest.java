package com.techelevator.model;

public class Guest {
    private String name;
    private String emailAddress;

    public Guest (String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public Guest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
