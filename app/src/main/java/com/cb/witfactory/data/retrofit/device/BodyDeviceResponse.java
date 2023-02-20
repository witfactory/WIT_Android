package com.cb.witfactory.data.retrofit.device;

import com.cb.witfactory.data.retrofit.user.GetUserResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BodyDeviceResponse {
    @SerializedName("Items")
    public ArrayList<DeviceResponse> body = new ArrayList<DeviceResponse>();

    public BodyDeviceResponse(){}

    public BodyDeviceResponse(ArrayList<DeviceResponse> body) {
        this.body = body;
    }

}
