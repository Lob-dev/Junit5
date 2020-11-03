package com.example.demo;

import javax.validation.constraints.Min;

public class Study {

    private StudyStatus status;

    @Min(1)
    private final int limit;

    public Study(int limit) {
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return this.limit;
    }
}
