package com.cb.witfactory.data.retrofit.user;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BodyUserResponse {
    @SerializedName("Items")
    public ArrayList<GetUserResponse> body = new ArrayList<GetUserResponse>();

    public BodyUserResponse(){}

    public BodyUserResponse(ArrayList<GetUserResponse> body) {
        this.body = body;
    }

}
