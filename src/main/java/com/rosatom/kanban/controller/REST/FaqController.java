package com.rosatom.kanban.controller.REST;

import com.rosatom.kanban.domain.Article;
import com.rosatom.kanban.dto.responses.ArticleResponse;
import com.rosatom.kanban.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("faq")
public class FaqController {
    @Autowired
    private FaqService faqService;

    @GetMapping
    public Iterable<Article> getAllArticles() {
        return faqService.findAll();
    }

    @GetMapping("{id}")
    public ArticleResponse getArticle(@PathVariable Long id) {
        return faqService.getArticle(id);
    }
}
