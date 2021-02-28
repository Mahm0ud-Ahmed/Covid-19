package com.example.covid_19.api;

import com.example.covid_19.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v3/covid-19/countries")
    Call<List<Country>> getCountry();

}
