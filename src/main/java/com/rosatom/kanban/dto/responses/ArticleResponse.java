package com.rosatom.kanban.dto.responses;

import org.springframework.http.HttpStatus;

public class ArticleResponse {
    private final Long id;
    private final HttpStatus status;

    public ArticleResponse(Long id, HttpStatus status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
