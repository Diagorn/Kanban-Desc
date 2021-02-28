package com.rosatom.kanban.controller.REST;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Article;
import com.rosatom.kanban.dto.responses.ArticleResponse;
import com.rosatom.kanban.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/articles")
public class FaqRestController {
    @Autowired
    private FaqService faqService;

    @GetMapping("")
    public Iterable<ArticleResponse> getAllArticles(Principal principal) {
        return faqService.findAllByJob(((Account) ((Authentication) principal).getPrincipal()).getJob().getId());
    }
}
