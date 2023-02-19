package com.cb.witfactory.data.retrofit;

import com.cb.witfactory.data.retrofit.user.GetUserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {


    @GET("user")
    Call<Object> getUser(
            @Query("id") String id
    );



}
