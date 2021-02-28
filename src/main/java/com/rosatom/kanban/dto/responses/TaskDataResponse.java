package com.rosatom.kanban.dto.responses;

import com.rosatom.kanban.domain.TaskStatus;

import java.util.GregorianCalendar;

public class TaskDataResponse {
    private Long id;

    private TaskStatus status;

    private String title;


    private String description;

    private GregorianCalendar start_date;

    private GregorianCalendar end_date;

    public TaskDataResponse(Long id, TaskStatus status, String title, String description, GregorianCalendar startDate, GregorianCalendar endDate) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.start_date = startDate;
        this.end_date = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getStart_date() {
        return start_date;
    }

    public void setStart_date(GregorianCalendar start_date) {
        this.start_date = start_date;
    }

    public GregorianCalendar getEnd_date() {
        return end_date;
    }

    public void setEnd_date(GregorianCalendar end_date) {
        this.end_date = end_date;
    }
}
