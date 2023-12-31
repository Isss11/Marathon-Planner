package com.application.marathonplanner.web;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class TrainingScheduleService {
    private List<DayPlan> trainingSchedule;
    private double weeklyIncrease;
    private boolean isMetric;
    private double skillMultiplier;
    private static final int WEEK_DAYS = 7;
    private static final double MARATHON_DISTANCE = 42.2;
    private static final double MILE_TO_KM = 1.6093;

    private static final String[] MONTH_STRINGS = { "January", "February", "March", "April", "May", "June", "July",
            "August",
            "September", "October", "November", "December" };

    public List<DayPlan> createTrainingSchedule(double weeklyIncrease, boolean isMetric, int skillLevel) {
        setWeeklyIncrease(weeklyIncrease);

        setIsMetric(isMetric);
        setSkillMultiplier(skillLevel);
        setTrainingSchedule();

        return getTrainingSchedule();
    }

    public void setTrainingSchedule() {
        double initialShort = 0.5 * getSkillMultiplier();
        double initialMedium = 1 * getSkillMultiplier();
        double initialLong = 2 * getSkillMultiplier();

        if (!getIsMetric()) {
            initialShort /= MILE_TO_KM;
            initialMedium /= MILE_TO_KM;
            initialLong /= MILE_TO_KM;
        }

        double curShort = initialShort;
        double curMedium = initialMedium;
        double curLong = initialLong;
        int day, dayThisWeek;
        DayPlan daySchedule;

        day = 0;
        dayThisWeek = 0;
        trainingSchedule = new ArrayList<DayPlan>();

        // continue training as long as the long run is shorter then a marathon distance
        while (getIsMetric() && curLong < MARATHON_DISTANCE
                || !getIsMetric() && curLong < MARATHON_DISTANCE / MILE_TO_KM) {
            daySchedule = getDayPlan(day, dayThisWeek, curShort, curMedium, curLong);
            trainingSchedule.add(daySchedule);

            ++day;
            ++dayThisWeek;

            // increase mileage at the end of each week
            if (dayThisWeek == WEEK_DAYS) {
                dayThisWeek = 0;
                curShort += getWeeklyIncrease() * curShort;
                curMedium += getWeeklyIncrease() * curMedium;
                curLong += getWeeklyIncrease() * curLong;
            }
        }
    }

    public List<DayPlan> getTrainingSchedule() {
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

    private void setSkillMultiplier(double skillLevel) {
        this.skillMultiplier = 1.5 * skillLevel;
    }

    public double getSkillMultiplier() {
        return this.skillMultiplier;
    }
}
