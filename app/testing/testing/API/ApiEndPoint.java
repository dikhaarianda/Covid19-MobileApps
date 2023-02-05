package com.example.testing.testing.API;

import com.example.covidtracker.model.ContinentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {
    @GET("continents")
    Call<List<ContinentModel>> getContinents();
}
