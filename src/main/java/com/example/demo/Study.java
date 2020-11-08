package com.example.demo;

import javax.validation.constraints.Min;

public class Study {

    private StudyStatus status;

    @Min(value = 0, message = "인원의 limit는 0보다 커야합니다.")
    private final int limit;
    
    private final String name;

    public Study(int limit) {
        if (limit < 0){
            throw new IllegalArgumentException("Limit 는 0보다 커야한다.");
        }
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
        name = "default";
    }

    public Study(Integer limit, String name) {
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
        this.name = name;
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

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }
}
