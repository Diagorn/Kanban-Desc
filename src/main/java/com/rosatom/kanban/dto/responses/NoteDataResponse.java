package com.rosatom.kanban.dto.responses;

import java.util.GregorianCalendar;

public class NoteDataResponse {
    private Long id;

    private String title;
    private String description;


    private GregorianCalendar start_date;
    private GregorianCalendar end_date;

    public NoteDataResponse (Long id, String title, String description, GregorianCalendar start_date, GregorianCalendar endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.end_date = endDate;
    }

    public NoteDataResponse () {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GregorianCalendar getEnd_date() {
        return end_date;
    }

    public void setEnd_date(GregorianCalendar end_date) {
        this.end_date = end_date;
    }

    public GregorianCalendar getStart_date() {
        return start_date;
    }

    public void setStart_date(GregorianCalendar start_date) {
        this.start_date = start_date;
    }
}

