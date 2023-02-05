package com.example.testing.API;

import com.example.testing.model.ContinentModel;
import com.example.testing.model.CountriesModel;
import com.example.testing.model.PieChartModel;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndPoint {
    @GET("continents")
    Call<List<ContinentModel>> getContinents();

    @GET("countries/{countries}?strict=true")
    Call<List<CountriesModel>> getCountries(@Path("countries") String countries);

    @GET("all")
    Call<PieChartModel> getCov19Worldwide();
}
