package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Article;
import com.rosatom.kanban.domain.Job;
import com.rosatom.kanban.dto.requests.ArticleQueryRequest;
import com.rosatom.kanban.dto.responses.ArticleResponse;
import com.rosatom.kanban.repos.FaqRepo;
import com.rosatom.kanban.utils.ServiceUtils;
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
        for (Article article : faqRepo.findAll()) {
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
        for (Article article : faqRepo.findAll()) {
            for (Job job : article.getJobs()) {
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

    public Iterable<ArticleResponse> findAllByQuery(Account account, ArticleQueryRequest request) {
        String query = request.getQuery();
        List<Article> result = ServiceUtils.collideLists(faqRepo.findAllByTitleContaining(query),
                faqRepo.findAllByContentContaining(query));
        List<ArticleResponse> data = new ArrayList<ArticleResponse>();
        for (Article article : result) {
            for (Job job : article.getJobs()) {
                if (article.getJobs().contains(job)) {
                    data.add(new ArticleResponse(
                            article.getId(),
                            HttpStatus.OK,
                            article.getTitle(),
                            article.getContent(),
                            article.getParent()
                    ));
                }
            }
        }
        return data;
    }
}
