package com.example.countrynameapp.service;

import com.example.countrynameapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {
    //Retrofit Interface

    @GET("countries")
    Call<Result> getResult();

}
