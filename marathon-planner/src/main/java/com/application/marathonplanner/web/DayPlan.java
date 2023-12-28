package com.application.marathonplanner.web;

public class DayPlan {
    private String date;
    private double distance;

    public DayPlan(String date) {
        setDate(date);
        setDistance(0);
    }

    public DayPlan(String date, double distance) {
        setDate(date);
        setDistance(distance);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return this.distance;
    }
}
