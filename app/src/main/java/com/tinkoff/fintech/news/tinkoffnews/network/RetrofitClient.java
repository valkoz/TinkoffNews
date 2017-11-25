package com.tinkoff.fintech.news.tinkoffnews.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinkoff.fintech.news.tinkoffnews.BuildConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private final String BASE_URL = "https://api.tinkoff.ru/v1/";

    private Api service;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }

        return instance;
    }

    private RetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
                .build();

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(Api.class);
    }

    public Api getService() {
        return this.service;
    }

    private Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    public static void sendRequest(final NewsCallback newsCallback) {
        Call<ApiResponse> responseCall = RetrofitClient.getInstance().getService().getAllNews();
        responseCall.enqueue(new retrofit2.Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.body() != null) {
                    List<NewsObject> newsObjects = response.body().getPayload();
                    if (newsObjects != null)
                        newsCallback.onSuccess(newsObjects);
                    else
                        newsCallback.onError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                newsCallback.onError();
            }
        });
    }

}
