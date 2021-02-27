package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Article;
import com.rosatom.kanban.dto.responses.ArticleResponse;
import com.rosatom.kanban.repos.FaqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FaqService {
    @Autowired
    private FaqRepo faqRepo;

    public Iterable<Article> findAll() {
        return faqRepo.findAll();
    }

    public Article findById(Long id) {
        return faqRepo.findById(id).get();
    }

    public ArticleResponse getArticle(Long id) {
        Article article = findById(id);
        if (article != null) {
            return new ArticleResponse(id, HttpStatus.OK);
        } else {
            return new ArticleResponse(-1L, HttpStatus.BAD_REQUEST);
        }
    }
}
