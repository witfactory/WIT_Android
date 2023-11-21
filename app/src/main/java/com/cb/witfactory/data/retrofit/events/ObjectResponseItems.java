package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ObjectResponseItems {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("response")
    public ArrayList<Object> response = new ArrayList<Object>();

}
