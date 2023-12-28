package com.application.marathonplanner.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
public class TrainingPlanController {
    private List<Double> trainingSchedule;
    private double weeklyIncrease;
    private boolean isMetric;
    private double skillMultiplier;

    @RequestMapping(value = "/trainingSchedule", method = RequestMethod.POST)
    public List<Double> trainingSchedule() {
        setWeeklyIncrease(0.10);
        setIsMetric(true);
        setSkillMultiplier(1.0);
        setTrainingSchedule();

        // System.out.println(runnerData.getSkillLevel());

        return getTrainingSchedule();
    }

    private void setTrainingSchedule() {
        double initialShort = 0.5 * getSkillMultiplier();
        double initialMedium = 1 * getSkillMultiplier();
        double initialLong = 2 * getSkillMultiplier();

        double curShort = initialShort;
        double curMedium = initialMedium;
        double curLong = initialLong;
        int day, dayThisWeek;

        day = 0;
        dayThisWeek = 0;
        trainingSchedule = new ArrayList<Double>();

        while (curLong < 42.2) {
            if (dayThisWeek <= 2) {
                trainingSchedule.add(curShort);
            } else if (dayThisWeek == 3) {
                trainingSchedule.add(curMedium);
            } else if (dayThisWeek == 4) {
                trainingSchedule.add(curLong);
            } else {
                trainingSchedule.add(0.0);
            }

            ++day;
            ++dayThisWeek;

            // increase mileage at the end of each week
            if (dayThisWeek == 7) {
                dayThisWeek = 0;
                curShort += getWeeklyIncrease() * curShort;
                curMedium += getWeeklyIncrease() * curMedium;
                curLong += getWeeklyIncrease() * curLong;
            }
        }
    }

    private List<Double> getTrainingSchedule() {
        return trainingSchedule;
    }

    private String getDateToday() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime currentTime = LocalDateTime.now();

        return dateFormatter.format(currentTime);
    }

    private void setWeeklyIncrease(double weeklyIncrease) {
        this.weeklyIncrease = weeklyIncrease;
    }

    private double getWeeklyIncrease() {
        return this.weeklyIncrease;
    }

    private void setIsMetric(boolean isMetric) {
        this.isMetric = isMetric;
    }

    private boolean getIsMetric() {
        return this.isMetric;
    }

    private void setSkillMultiplier(double skillLevel) {
        this.skillMultiplier = 1.5 * skillLevel;
    }

    private double getSkillMultiplier() {
        return this.skillMultiplier;
    }
}
