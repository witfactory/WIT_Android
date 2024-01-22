package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class ObjectResponseEventsRealtime {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("response")
    public ArrayList<Metric> response;

    public ObjectResponseEventsRealtime(){}


}



