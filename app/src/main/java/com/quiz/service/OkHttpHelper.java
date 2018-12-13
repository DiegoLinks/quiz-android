package com.quiz.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpHelper {

    public static OkHttpClient getClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        return builder
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

}
