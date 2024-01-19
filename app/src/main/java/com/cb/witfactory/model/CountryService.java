package com.cb.witfactory.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {
    @GET("countries/capital")
    Call<CountryResponse> getCountries();
}

