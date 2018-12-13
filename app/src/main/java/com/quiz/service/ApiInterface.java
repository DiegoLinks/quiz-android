package com.quiz.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quiz.models.Quiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiInterface {

    public static String API_URL = "www.quizapi.com/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpHelper.getClient())
            .build();

    //Retorna as questões da API
    @GET("questions")
    Call<List<Quiz>> getQuestions();
}


