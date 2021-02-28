package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Article;
import com.rosatom.kanban.domain.Job;
import com.rosatom.kanban.dto.responses.ArticleResponse;
import com.rosatom.kanban.repos.FaqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class FaqService {
    @Autowired
    private FaqRepo faqRepo;

    public Iterable<ArticleResponse> findAll() {
        List<ArticleResponse> data = new ArrayList<>();
        for (Article article: faqRepo.findAll()) {
            data.add(
                    new ArticleResponse(
                        article.getId(),
                        HttpStatus.OK,
                        article.getTitle(),
                        article.getContent(),
                        article.getParent()
                    )
            );
        }
        return data;
    }

    public Iterable<ArticleResponse> findAllByJob(Long jobId) {
        List<ArticleResponse> data = new ArrayList();
        for (Article article: faqRepo.findAll()) {
            for(Job job: article.getJobs()) {
                if (job.getId().equals(jobId)) {
                    data.add(
                        new ArticleResponse(
                            article.getId(),
                            HttpStatus.OK,
                            article.getTitle(),
                            article.getContent(),
                            article.getParent()
                        )
                );
                }
            }
        }
        return data;
    }

    public Article findById(Long id) {
        return faqRepo.findById(id).get();
    }

//    public ArticleResponse getArticle(Long id) {
//        Article article = findById(id);
//        if (article != null) {
//            return new ArticleResponse(
//                    article.getId(),
//                    HttpStatus.OK,
//                    article.getTitle(),
//                    article.getContent(),
//                    article.getParent()
//            );
//        } else {
//            return new ArticleResponse(-1L, HttpStatus.BAD_REQUEST,);
//        }
//    }
}
