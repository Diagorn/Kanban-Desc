package com.rosatom.kanban.controller;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.service.AccountService;
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

    @Autowired
    private AccountService accountService;
    
    @GetMapping("/")
    public String getMainPage(Model model, @AuthenticationPrincipal Account account) {
        Account account1 = accountService.findByUsername(account.getUsername());
        tokenService.createToken(account1);
        model.addAttribute("token", account1.getToken().getToken());
        model.addAttribute("user", account1);
        return "main_page";
    }
}
