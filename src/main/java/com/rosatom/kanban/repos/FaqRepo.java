package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface FaqRepo extends CrudRepository<Article, Long> {

}
