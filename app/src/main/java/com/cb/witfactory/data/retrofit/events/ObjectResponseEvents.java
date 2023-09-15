package com.cb.witfactory.data.retrofit.events;

import com.cb.witfactory.data.retrofit.user.GetUserResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ObjectResponseEvents {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("Items")
    public ArrayList<PayloadResponse> body = new ArrayList<PayloadResponse>();

    public ObjectResponseEvents(){}

    public ObjectResponseEvents(Integer statusCode, ArrayList<PayloadResponse> body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<PayloadResponse> getBody() {
        return body;
    }

    public void setBody(ArrayList<PayloadResponse> body) {
        this.body = body;
    }
}
