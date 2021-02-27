package com.rosatom.kanban.dto.responses;

import org.springframework.http.HttpStatus;

public class NoteResponse {
    private final long id;
    private final HttpStatus status;

    public NoteResponse(long id, HttpStatus status) {
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
