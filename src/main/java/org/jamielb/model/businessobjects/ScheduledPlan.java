package org.jamielb.model.businessobjects;

import java.util.List;

public class ScheduledPlan {

    private int id;
    private ReadingPlan readingPlan;
    private String startDate;
    private String endDate;
    private List<DailyReading> dailyReadings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReadingPlan getReadingPlan() {
        return readingPlan;
    }

    public void setReadingPlan(ReadingPlan readingPlan) {
        this.readingPlan = readingPlan;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<DailyReading> getDailyReadings() {
        return dailyReadings;
    }

    public void setDailyReadings(List<DailyReading> dailyReadings) {
        this.dailyReadings = dailyReadings;
    }

}
