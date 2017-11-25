package com.tinkoff.fintech.news.tinkoffnews.network;


public class PublicationDate {

    private Long milliseconds;

    public PublicationDate(Long timestamp) {
        milliseconds = timestamp;
    }

    public Long getMilliseconds() {
        return milliseconds;
    }
}
