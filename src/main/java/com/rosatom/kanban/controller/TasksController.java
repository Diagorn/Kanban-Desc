package com.rosatom.kanban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tasks")
public class TasksController {
    @GetMapping("/")
    public String getTasksMainPage() {
        return "tasks";
    }
}
