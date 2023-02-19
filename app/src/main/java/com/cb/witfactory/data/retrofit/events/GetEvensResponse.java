package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetEvensResponse {


    @SerializedName("payload")
    public ArrayList<PayloadResponse> listDevice = new ArrayList<PayloadResponse>();

    @SerializedName("device_id")
    private String device_id;

    public GetEvensResponse(ArrayList<PayloadResponse> listDevice, String device_id) {
        this.listDevice = listDevice;
        this.device_id = device_id;
    }

    public ArrayList<PayloadResponse> getListDevice() {
        return listDevice;
    }

    public void setListDevice(ArrayList<PayloadResponse> listDevice) {
        this.listDevice = listDevice;
    }
}
