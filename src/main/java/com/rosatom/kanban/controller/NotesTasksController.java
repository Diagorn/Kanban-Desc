package com.rosatom.kanban.controller;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.service.AccountService;
import com.rosatom.kanban.service.TokenService;
import com.rosatom.kanban.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
public class NotesTasksController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ServiceUtils serviceUtils;

    @GetMapping("")
    public String getNotesAndTasksPage(@AuthenticationPrincipal Account user, Model model) {
        user = accountService.findByUsername(user.getUsername());
        tokenService.createToken(user);
        model.addAttribute("tasks", user.getOwnedTasks());
        model.addAttribute("notes", user.getNotes());
        model.addAttribute("utils", serviceUtils);
        model.addAttribute("user", user);
        model.addAttribute("token", user.getToken().getToken());

        return "notes_tasks";
    }
}
