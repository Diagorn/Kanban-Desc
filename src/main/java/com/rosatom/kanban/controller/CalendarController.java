package com.rosatom.kanban.controller;

import org.springframework.stereotype.Controller;

@Controller(value = "/calendar")
public class CalendarController {
    public String getCalendarMainPage() {
        return "calendar";
    }
}
