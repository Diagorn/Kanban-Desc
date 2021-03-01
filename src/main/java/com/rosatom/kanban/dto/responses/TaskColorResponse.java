package com.rosatom.kanban.dto.responses;

import org.springframework.http.HttpStatus;

public class TaskColorResponse {
    private final long id;
    private final HttpStatus status;

    public TaskColorResponse(long id, HttpStatus status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
