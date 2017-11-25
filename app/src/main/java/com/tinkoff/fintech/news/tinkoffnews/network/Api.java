package com.tinkoff.fintech.news.tinkoffnews.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("news")
    Call<ApiResponse> getAllNews();

}
