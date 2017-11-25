package com.tinkoff.fintech.news.tinkoffnews.network;

import com.tinkoff.fintech.news.tinkoffnews.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("news")
    Call<ApiResponse> getAllNews();

}
