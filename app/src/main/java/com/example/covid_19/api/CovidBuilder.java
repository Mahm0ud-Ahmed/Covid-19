package com.example.covid_19.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidBuilder {

    private final static String BASE_URL = "https://disease.sh/";
    private static Retrofit retrofit;

    public static Retrofit getInstanceRetrofit(){
        if (retrofit == null){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }

}
