package com.rosatom.kanban.dto.requests;

public class ArticleQueryRequest {
    private String query;

    public ArticleQueryRequest(String query) {
        this.query = query;
    }

    public ArticleQueryRequest() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
