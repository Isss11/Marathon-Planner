package com.application.marathonplanner.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingPlanController {

    @Autowired
    private TrainingScheduleService trainingScheduleService;

    @RequestMapping(value = "/trainingSchedule", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<DayPlan> trainingSchedule(@RequestBody Runner runnerData) {
        return trainingScheduleService.createTrainingSchedule(runnerData.getWeeklyIncrease(), runnerData.getIsMetric(),
                runnerData.getStartingWeeklyDistance());
    }
}
