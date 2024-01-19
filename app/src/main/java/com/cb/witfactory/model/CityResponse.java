package com.cb.witfactory.model;

// CityResponse.java

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private List<String> cities;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getData() {
        return cities;
    }
}
