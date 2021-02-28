package com.rosatom.kanban.dto.responses;

import com.rosatom.kanban.domain.Article;
import org.springframework.http.HttpStatus;

public class ArticleResponse {
    private final Long id;
    private final HttpStatus status;
    private String title;
    private String content;
    private Long parent = null;

    public ArticleResponse(Long id, HttpStatus status, String title, String content, Article parent) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.content = content;
        if (parent != null) {
            this.parent = parent.getId();
        }
    }

    public Long getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getParent() {
        return parent;
    }
}
