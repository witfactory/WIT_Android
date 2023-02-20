package com.cb.witfactory.data.retrofit;

import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("user")
    Call<ObjectResponseUser> getUser(
            @Query("id") String id
    );



}
