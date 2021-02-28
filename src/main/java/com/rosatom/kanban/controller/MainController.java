package com.rosatom.kanban.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String getMainPage(Model model, Principal principal) {
        model.addAttribute("user", ((Authentication) principal).getPrincipal());
        return "main_page";
    }
}
