package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

public class ObjectResponseAlarmValve {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("body")
    public String message;

    public ObjectResponseAlarmValve(){}


}



