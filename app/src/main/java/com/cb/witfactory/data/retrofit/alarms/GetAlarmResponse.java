package com.cb.witfactory.data.retrofit.alarms;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetAlarmResponse {

    @SerializedName("alarms")
    public ArrayList<Alarm> listDevice = new ArrayList<Alarm>();

    public GetAlarmResponse(ArrayList<Alarm> listDevice) {
        this.listDevice = listDevice;
    }

    public ArrayList<Alarm> getListDevice() {
        return listDevice;
    }

    public void setListDevice(ArrayList<Alarm> listDevice) {
        this.listDevice = listDevice;
    }
}
