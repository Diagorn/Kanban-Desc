package com.rosatom.kanban.dto.requests;

import java.util.GregorianCalendar;

public class NoteRequest {
    private String title;
    private String description;
    private GregorianCalendar start_date;
    private GregorianCalendar end_date;

    public NoteRequest() {
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
