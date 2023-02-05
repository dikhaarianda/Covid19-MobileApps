package com.example.testing.testing.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String BASE_URL = "https://disease.sh/v3/covid-19/";
    private static Retrofit retrofit;

    public static ApiEndPoint endPoint() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiEndPoint.class);
    }
}
