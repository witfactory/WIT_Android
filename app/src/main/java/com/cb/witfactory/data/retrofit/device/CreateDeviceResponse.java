package com.cb.witfactory.data.retrofit.device;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

    public class CreateDeviceResponse {

    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("device_created")
    private String device_created;

}
