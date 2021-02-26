package com.rosatom.kanban.controller;

import org.springframework.stereotype.Controller;

@Controller(value = "/faq")
public class FaqController {
    public String getFaqMainPage() {
        return "faq";
    }
}
