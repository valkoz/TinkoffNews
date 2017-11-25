package com.tinkoff.fintech.news.tinkoffnews.network;

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
