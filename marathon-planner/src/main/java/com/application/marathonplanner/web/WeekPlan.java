package com.application.marathonplanner.web;

import java.util.ArrayList;;

public class WeekPlan {
    private ArrayList<DayPlan> daySchedules;
    private int week;
    private double distance;

    public WeekPlan(ArrayList<DayPlan> daySchedules, int week, double weeklyDistance) {
        setDaySchedules(daySchedules);
        setWeek(week);
        setDistance(weeklyDistance);
    }

    public ArrayList<DayPlan> getDaySchedules() {
        return this.daySchedules;
    }

    public void setDaySchedules(ArrayList<DayPlan> daySchedules) {
        this.daySchedules = daySchedules;
    }

    public int getWeek() {
        return this.week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
