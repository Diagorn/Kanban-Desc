package com.rosatom.kanban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "/notes")
public class NoteController {
    @GetMapping("/")
    public String getMainNotesPage() {
        return "notes";
    }
}
