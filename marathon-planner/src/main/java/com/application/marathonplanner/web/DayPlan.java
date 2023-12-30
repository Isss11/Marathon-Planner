package com.application.marathonplanner.web;

public class DayPlan {
    private String date; // full date string
    private String weekDay;
    private String month;
    private int day;
    private int year;
    private double distance;
    private String runTitle;

    public DayPlan(String date, String weekDay, String month, int day, int year, double distance, String runTitle) {
        setDate(date);
        setDistance(0);
        setWeekDay(weekDay);
        setMonth(month);
        setDay(day);
        setYear(year);
        setDistance(distance);
        setRunTitle(runTitle);
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

    public String getWeekDay() {
        return this.weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRunTitle() {
        return this.runTitle;
    }

    public void setRunTitle(String runTitle) {
        this.runTitle = runTitle;
    }
}
