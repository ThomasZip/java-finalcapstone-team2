package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SpecialHours {



    private Date date;
    private String start;
    private String end;
    @JsonProperty("is_overnight")
    private boolean isOvernight;
    @JsonProperty("is_closed")
    private boolean isClosed;

   public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isOvernight() {
        return isOvernight;
    }

    public void setOvernight(boolean overnight) {
        isOvernight = overnight;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
