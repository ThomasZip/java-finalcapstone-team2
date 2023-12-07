package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BusinessHours {
    private List<HoursRange> open;
    @JsonProperty("hours_type")
    private String hoursType;
    @JsonProperty("is_open_now")
    private boolean isOpenNow;

    public List<HoursRange> getOpen() {
        return open;
    }

    public void setOpen(List<HoursRange> open) {
        this.open = open;
    }

    public String getHoursType() {
        return hoursType;
    }

    public void setHoursType(String hoursType) {
        this.hoursType = hoursType;
    }

    public boolean isOpenNow() {
        return isOpenNow;
    }

    public void setOpenNow(boolean openNow) {
        isOpenNow = openNow;


    }
}
