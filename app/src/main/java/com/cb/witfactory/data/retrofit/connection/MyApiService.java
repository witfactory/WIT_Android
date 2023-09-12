package com.cb.witfactory.data.retrofit.connection;

import com.cb.witfactory.data.retrofit.device.ObjectResponseDevice;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("user")
    Call<ObjectResponseUser> getUser(
            @Query("id") String id
    );


    @GET("device")
    Call<ObjectResponseDevice> getDevice(
            @Query("user_id") String user_id,
            @Query("device_type") String device_type
    );


}
