package com.cb.witfactory.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeonamesApi {
    @GET("citiesJSON")
    Call<List<City>> getCities(@Query("country") String countryCode);
}

