package com.rosatom.kanban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {
    @GetMapping("/")
    public String getMainNotesPage() {
        return "notes";
    }
}
