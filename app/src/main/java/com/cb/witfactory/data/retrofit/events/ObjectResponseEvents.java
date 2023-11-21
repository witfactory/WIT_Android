package com.cb.witfactory.data.retrofit.events;

import com.cb.witfactory.data.retrofit.user.GetUserResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectResponseEvents {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("response")
    public Map<String, DeviceMetrics> response;

    public ObjectResponseEvents(){}


}



