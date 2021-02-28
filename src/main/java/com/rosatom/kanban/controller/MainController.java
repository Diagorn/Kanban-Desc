package com.rosatom.kanban.controller;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("/")
    public String getMainPage(Model model, @AuthenticationPrincipal Account account) {
        tokenService.createToken(account);
        model.addAttribute("token", account.getToken().getToken());
        model.addAttribute("user", account);
        return "main_page";
    }
}
