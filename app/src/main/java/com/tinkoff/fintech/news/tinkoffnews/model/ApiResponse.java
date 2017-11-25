package com.tinkoff.fintech.news.tinkoffnews.model;

import com.tinkoff.fintech.news.tinkoffnews.model.NewsObject;

import java.util.List;

public class ApiResponse {
    private String status;
    private List<NewsObject> payload;

    public String getStatus() {
        return status;
    }

    public List<NewsObject> getPayload() {
        return payload;
    }

}
