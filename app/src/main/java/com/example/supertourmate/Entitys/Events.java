package com.example.supertourmate.Entitys;

public class Events {
    private String eventID;
    private String eventName;
    private String start_Loc;
    private String destination;
    private String depDate;
    private int budget;

    public Events() {
    }

    public Events(String eventID, String eventName, String start_Loc, String destination, String depDate, int budget) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.start_Loc = start_Loc;
        this.destination = destination;
        this.depDate = depDate;
        this.budget = budget;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStart_Loc() {
        return start_Loc;
    }

    public void setStart_Loc(String start_Loc) {
        this.start_Loc = start_Loc;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
