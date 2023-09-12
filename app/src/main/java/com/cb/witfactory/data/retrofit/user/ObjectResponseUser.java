package com.cb.witfactory.data.retrofit.user;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ObjectResponseUser {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("user")
    public ArrayList<GetUserResponse> body = new ArrayList<GetUserResponse>();

    public ObjectResponseUser(){}

    public ObjectResponseUser(Integer statusCode, ArrayList<GetUserResponse> body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<GetUserResponse> getBody() {
        return body;
    }

    public void setBody(ArrayList<GetUserResponse> body) {
        this.body = body;
    }
}
