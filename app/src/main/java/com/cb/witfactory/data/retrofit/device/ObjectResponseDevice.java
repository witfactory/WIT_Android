package com.cb.witfactory.data.retrofit.device;

import com.google.gson.annotations.SerializedName;

public class ObjectResponseDevice {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("body")
 private BodyDeviceResponse body;

    public ObjectResponseDevice(){}

    public ObjectResponseDevice(Integer statusCode, BodyDeviceResponse body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public BodyDeviceResponse getBody() {
        return body;
    }

    public void setBody(BodyDeviceResponse body) {
        this.body = body;
    }
}
