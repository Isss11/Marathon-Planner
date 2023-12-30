package com.application.marathonplanner.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.util.Date;
import java.io.ByteArrayOutputStream;

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

    public ResponseEntity<byte[]> getTrainingPlanPdf() throws IOException, DocumentException {
        ByteArrayOutputStream pdfStream = PdfUtils.generatePdfStream(getTrainingSchedule());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
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

        // // Add day plans for all days already passed in current week for easier
        // calendar
        // // implementation in UI.
        // int daysPreceding = getDate(0).getDay();
        // int i = daysPreceding * -1;

        // while (i < 0) {
        // daySchedule = getDaySchedule(i);
        // daySchedule.setDistance(0);
        // trainingSchedule.add(daySchedule);

        // ++i;
        // }

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

        daySchedule = getDaySchedule(day);

        if (dayThisWeek <= 2) {
            daySchedule.setDistance(curShort);
        } else if (dayThisWeek == 4) {
            daySchedule.setDistance(curMedium);
        } else if (dayThisWeek == 6) {
            daySchedule.setDistance(curLong);
        }

        return daySchedule;
    }

    private DayPlan getDaySchedule(int day) {
        DayPlan daySchedule;
        Calendar calendarDate;
        SimpleDateFormat dateFormatter;

        calendarDate = Calendar.getInstance();
        calendarDate.setTime(getDate(day));

        dateFormatter = new SimpleDateFormat("EEEE");

        daySchedule = new DayPlan(getDateString(calendarDate), dateFormatter.format(calendarDate.getTime()),
                MONTH_STRINGS[calendarDate.get(Calendar.MONTH)],
                calendarDate.get(Calendar.DAY_OF_MONTH), calendarDate.get(Calendar.YEAR));

        return daySchedule;
    }

    private Date getDate(int daysAfterToday) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.add(Calendar.DATE, daysAfterToday);

        return calendarDate.getTime();
    }

    private String getDateString(Calendar date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormatter.format(date.getTime());
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
