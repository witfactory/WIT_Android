package com.cb.witfactory.data.retrofit.alarms;

import com.google.gson.annotations.SerializedName;

public class VincularDeviceSensor {

    @SerializedName("valve_id")
    private String valve_id;

    @SerializedName("sensor_list")
    private String[] sensor_list;

    public VincularDeviceSensor(String valve_id, String[] sensor_list) {
        this.valve_id = valve_id;
        this.sensor_list = sensor_list;
    }

    public String getValve_id() {
        return valve_id;
    }

    public void setValve_id(String valve_id) {
        this.valve_id = valve_id;
    }

    public String[] getSensor_list() {
        return sensor_list;
    }

    public void setSensor_list(String[] sensor_list) {
        this.sensor_list = sensor_list;
    }
}
