package com.application.marathonplanner.web;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TrainingScheduleService {
    private ArrayList<WeekPlan> trainingSchedule;
    private double weeklyIncrease;
    private boolean isMetric;
    private double startingWeeklyDistance;
    private static final int WEEK_DAYS = 7;
    private static final double MINIMUM_WEEKLY_DISTANCE = 5;
    private static final double MARATHON_DISTANCE = 42.2;
    private static final double MILE_TO_KM = 1.6093;

    public ArrayList<WeekPlan> createTrainingSchedule(double weeklyIncrease, boolean isMetric,
            double startingWeeklyDistance) {
        setWeeklyIncrease(weeklyIncrease);
        setIsMetric(isMetric);
        setStartingWeeklyDistance(startingWeeklyDistance);
        setTrainingSchedule();

        return getTrainingSchedule();
    }

    public void setTrainingSchedule() {
        // total allocation of running (see decimals below) adds up to 100%
        double initialShort = 0.3 * getStartingWeeklyDistance() / 3; // splitting distance across 5 days
        double initialMedium = 0.25 * getStartingWeeklyDistance();
        double initialLong = 0.45 * getStartingWeeklyDistance();

        if (!getIsMetric()) {
            initialShort /= MILE_TO_KM;
            initialMedium /= MILE_TO_KM;
            initialLong /= MILE_TO_KM;
        }

        double weeklyDistance, curShort, curMedium, curLong;
        int day, dayThisWeek, week;
        DayPlan daySchedule;
        WeekPlan weekSchedule;
        ArrayList<DayPlan> daySchedules;

        curShort = initialShort;
        curMedium = initialMedium;
        curLong = initialLong;

        day = 0;
        dayThisWeek = 0;
        week = 0;
        weeklyDistance = 0;

        trainingSchedule = new ArrayList<WeekPlan>();
        daySchedules = new ArrayList<DayPlan>();

        // continue training as long as the long run is shorter then a marathon distance
        while (getIsMetric() && curLong < MARATHON_DISTANCE
                || !getIsMetric() && curLong < MARATHON_DISTANCE / MILE_TO_KM) {
            daySchedule = getDayPlan(day, dayThisWeek, curShort, curMedium, curLong);
            daySchedules.add(daySchedule);

            weeklyDistance += daySchedule.getDistance();

            ++day;
            ++dayThisWeek;

            // increase mileage at the end of each week
            if (dayThisWeek == WEEK_DAYS) {
                dayThisWeek = 0;
                curShort += getWeeklyIncrease() * curShort;
                curMedium += getWeeklyIncrease() * curMedium;
                curLong += getWeeklyIncrease() * curLong;

                // add in week schedule to the training schedule
                weekSchedule = new WeekPlan(daySchedules, week + 1, weeklyDistance);
                trainingSchedule.add(weekSchedule);

                // reset daySchedules for next week
                daySchedules = new ArrayList<DayPlan>();
                weeklyDistance = 0;

                // TODO: Add in remaining days if we need did reach a multiple of 7 on the final
                // day

                ++week;
            }
        }
    }

    public ArrayList<WeekPlan> getTrainingSchedule() {
        return trainingSchedule;
    }

    public DayPlan getDayPlan(int day, int dayThisWeek, double curShort, double curMedium, double curLong) {
        DayPlan daySchedule;
        double distance;
        String runTitle;

        distance = 0;
        runTitle = "Rest";

        if (dayThisWeek <= 2) {
            distance = curShort;
            runTitle = "Easy Run";
        } else if (dayThisWeek == 4) {
            distance = curMedium;
            runTitle = "Medium Run";
        } else if (dayThisWeek == 6) {
            distance = curLong;
            runTitle = "Long Run";
        }

        daySchedule = getDaySchedule(day, distance, runTitle);

        return daySchedule;
    }

    private DayPlan getDaySchedule(int day, double distance, String runTitle) {
        DayPlan daySchedule;

        daySchedule = new DayPlan(day + 1, distance, runTitle);

        return daySchedule;
    }

    private void setWeeklyIncrease(double weeklyIncrease) {
        this.weeklyIncrease = weeklyIncrease;
    }

    public double getWeeklyIncrease() {
        return this.weeklyIncrease;
    }

    private void setIsMetric(boolean isMetric) {
        this.isMetric = isMetric;
    }

    public boolean getIsMetric() {
        return this.isMetric;
    }

    private void setStartingWeeklyDistance(double startingWeeklyDistance) {
        this.startingWeeklyDistance = startingWeeklyDistance > MINIMUM_WEEKLY_DISTANCE ? startingWeeklyDistance
                : MINIMUM_WEEKLY_DISTANCE;
    }

    public double getStartingWeeklyDistance() {
        return this.startingWeeklyDistance;
    }
}
