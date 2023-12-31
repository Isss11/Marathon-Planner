package com.application.marathonplanner.web;

public class DayPlan {
    private int day;
    private double distance;
    private String runTitle;

    public DayPlan(int day) {
        setDay(day);
        setDistance(0);
        setRunTitle(null);
    }

    public DayPlan(int day, double distance, String runTitle) {
        setDay(day);
        setDistance(distance);
        setRunTitle(runTitle);
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return this.distance;
    }

    public String getRunTitle() {
        return this.runTitle;
    }

    public void setRunTitle(String runTitle) {
        this.runTitle = runTitle;
    }
}
