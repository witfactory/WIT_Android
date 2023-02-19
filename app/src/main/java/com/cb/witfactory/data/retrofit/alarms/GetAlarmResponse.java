package com.cb.witfactory.data.retrofit.alarms;

import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetAlarmResponse {

    @SerializedName("alarms")
    public ArrayList<AlamResponse> listDevice = new ArrayList<AlamResponse>();

    public GetAlarmResponse(ArrayList<AlamResponse> listDevice) {
        this.listDevice = listDevice;
    }

    public ArrayList<AlamResponse> getListDevice() {
        return listDevice;
    }

    public void setListDevice(ArrayList<AlamResponse> listDevice) {
        this.listDevice = listDevice;
    }
}
