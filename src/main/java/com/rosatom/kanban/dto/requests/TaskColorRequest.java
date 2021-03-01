package com.rosatom.kanban.dto.requests;

public class TaskColorRequest {
    private String color;

    public TaskColorRequest(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
