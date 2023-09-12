package com.cb.witfactory.data.retrofit.device;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ObjectResponseDevice {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("device")
    public ArrayList<DeviceResponse> body = new ArrayList<DeviceResponse>();

    public ObjectResponseDevice(){}

    public ObjectResponseDevice(Integer statusCode, ArrayList<DeviceResponse> body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<DeviceResponse> getBody() {
        return body;
    }

    public void setBody(ArrayList<DeviceResponse> body) {
        this.body = body;
    }
}
