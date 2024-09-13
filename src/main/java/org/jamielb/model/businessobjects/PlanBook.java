package org.jamielb.model.businessobjects;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanBook {

    private int planId;
    private int bookId;
    private int sequence;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlanBook planBook = (PlanBook) o;
        return planId == planBook.planId && bookId == planBook.bookId && sequence == planBook.sequence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, bookId, sequence);
    }

}
