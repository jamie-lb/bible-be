package org.jamielb.model.businessobjects;

import java.util.List;

public class ReadingPlan {

    private int planId;
    private String planName;
    private BibleVersion version;
    private List<PlanBook> books;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public BibleVersion getVersion() {
        return version;
    }

    public void setVersion(BibleVersion version) {
        this.version = version;
    }

    public List<PlanBook> getBooks() {
        return books;
    }

    public void setBooks(List<PlanBook> books) {
        this.books = books;
    }

}
