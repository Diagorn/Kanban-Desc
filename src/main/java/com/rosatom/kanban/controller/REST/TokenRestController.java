package com.rosatom.kanban.controller.REST;


import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Token;
import com.rosatom.kanban.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/token")
public class TokenRestController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    @ResponseBody
    public Token getToken(Principal principal) {
        return tokenService.createToken((Account) ((Authentication) principal).getPrincipal());
    }
}
