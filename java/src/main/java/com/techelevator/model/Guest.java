package com.techelevator.model;

public class Guest {
    private int guestId;
    private String name;
    private String emailAddress;

    public Guest (int id, String name, String emailAddress) {
        this.guestId = id;
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

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
}
