package com.rosatom.kanban.dto.responses;

import org.springframework.http.HttpStatus;

public class TaskStatusResponse {
    private final Long id;
    private final HttpStatus code;

    public TaskStatusResponse(Long id, HttpStatus code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public HttpStatus getCode() {
        return code;
    }
}
