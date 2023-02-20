package com.cb.witfactory.data.retrofit.user;

import com.google.gson.annotations.SerializedName;

public class ObjectResponseUser {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("body")
 private  BodyUserResponse body;

    public ObjectResponseUser(){}

    public ObjectResponseUser(Integer statusCode, BodyUserResponse body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public BodyUserResponse getBody() {
        return body;
    }

    public void setBody(BodyUserResponse body) {
        this.body = body;
    }
}
