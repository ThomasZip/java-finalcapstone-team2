package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HoursRange {
    @JsonProperty("is_overnight")
    private boolean isOvernight;
    private int start;
    private int end;
    private int day;

    public boolean isOvernight() {
        return isOvernight;
    }

    public void setOvernight(boolean overnight) {
        isOvernight = overnight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
