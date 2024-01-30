package com.cb.witfactory.data.retrofit.connection;

import com.cb.witfactory.data.retrofit.alarms.Alarm;
import com.cb.witfactory.data.retrofit.device.CreateDevice;
import com.cb.witfactory.data.retrofit.device.CreateDeviceResponse;
import com.cb.witfactory.data.retrofit.device.ObjectResponseDevice;
import com.cb.witfactory.data.retrofit.events.EventsRealtime;
import com.cb.witfactory.data.retrofit.events.ObjectResponseAlarm;
import com.cb.witfactory.data.retrofit.events.ObjectResponseEvents;
import com.cb.witfactory.data.retrofit.events.ObjectResponseEventsRealtime;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.data.retrofit.user.UpdateUserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("user")
    Call<ObjectResponseUser> getUser(
            @Query("id") String id
    );

    @POST("user")
    Call<UpdateUserResponse> updateUser(
            @Body UpdateUserResponse updateUserRequest
    );


    @GET("device")
    Call<ObjectResponseDevice> getDevice(
            @Query("user_id") String user_id
    );


    @GET("events")
    Call<ObjectResponseEvents> getEvents(
            @Query("device_id") String device_id,
            @Query("from") String from,
            @Query("to") String to
    );


    @POST("events")
    Call<ObjectResponseEventsRealtime> getEventsRealtime(
            @Body EventsRealtime eventsRealtime
            );


    @POST("create")
    Call<CreateDeviceResponse> createDevice(@Body CreateDevice createDevice);

    @POST("alarms")
    Call<ObjectResponseAlarm> setAlarm(
            @Body ArrayList<Alarm> alarms
    );

}
