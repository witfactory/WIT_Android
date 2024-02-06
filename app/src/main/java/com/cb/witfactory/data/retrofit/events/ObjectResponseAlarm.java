package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ObjectResponseAlarm {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("Message")
    public String message;

    public ObjectResponseAlarm(){}


}



