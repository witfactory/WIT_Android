package com.cb.witfactory.data.retrofit.device;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDeviceResponse {

    @SerializedName("Items")
    public ArrayList<DeviceResponse> listDevice = new ArrayList<DeviceResponse>();

    @SerializedName("Count")
    private Integer count;

    @SerializedName("ScannedCount")
    private Integer scannedCount;

    public GetDeviceResponse(ArrayList<DeviceResponse> listDevice, Integer count, Integer scannedCount) {
        this.listDevice = listDevice;
        this.count = count;
        this.scannedCount = scannedCount;
    }

    public ArrayList<DeviceResponse> getListDevice() {
        return listDevice;
    }

    public void setListDevice(ArrayList<DeviceResponse> listDevice) {
        this.listDevice = listDevice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getScannedCount() {
        return scannedCount;
    }

    public void setScannedCount(Integer scannedCount) {
        this.scannedCount = scannedCount;
    }
}
