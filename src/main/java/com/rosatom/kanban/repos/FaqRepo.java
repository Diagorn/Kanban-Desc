package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FaqRepo extends CrudRepository<Article, Long> {
    List<Article> findAllByContentContaining(String content);
    List<Article> findAllByTitleContaining(String title);
}
