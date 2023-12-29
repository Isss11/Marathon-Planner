package com.application.marathonplanner.web;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

@Service
public class TrainingScheduleService {
    private List<DayPlan> trainingSchedule;
    private double weeklyIncrease;
    private boolean isMetric;
    private double skillMultiplier;
    private static final int WEEK_DAYS = 7;
    private static final double MARATHON_DISTANCE = 42.2;
    private static final double MILE_TO_KM = 1.6093;

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

        daySchedule = new DayPlan(getDate(day));

        if (dayThisWeek <= 2) {
            daySchedule.setDistance(curShort);
        } else if (dayThisWeek == 4) {
            daySchedule.setDistance(curMedium);
        } else if (dayThisWeek == 6) {
            daySchedule.setDistance(curLong);
        }

        return daySchedule;
    }

    public String getDate(int daysAfterToday) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.add(Calendar.DATE, daysAfterToday);

        // formatting the date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormatter.format(calendarDate.getTime());
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
