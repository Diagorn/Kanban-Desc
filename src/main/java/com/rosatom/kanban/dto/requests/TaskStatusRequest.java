package com.rosatom.kanban.dto.requests;

import com.rosatom.kanban.domain.TaskStatus;

public class TaskStatusRequest {
    private TaskStatus status;

    public TaskStatusRequest() {
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
