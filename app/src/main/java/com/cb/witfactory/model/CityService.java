package com.cb.witfactory.model;

// CityService.java

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// ...

public interface CityService {

    @POST("countries/cities")
    Call<CityResponse> getCities(@Body Map<String, String> requestBody);
}

