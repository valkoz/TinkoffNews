package com.tinkoff.fintech.news.tinkoffnews.model;


public class PublicationDate {

    private Long milliseconds;

    public PublicationDate(Long timestamp) {
        milliseconds = timestamp;
    }

    public Long getMilliseconds() {
        return milliseconds;
    }
}
