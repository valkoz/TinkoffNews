package com.tinkoff.fintech.news.tinkoffnews.network;

import com.tinkoff.fintech.news.tinkoffnews.model.NewsObject;

import java.util.List;

public interface NewsCallback {

    void onSuccess(List<NewsObject> newsObjects);
    void onError();

}
