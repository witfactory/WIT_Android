package com.cb.witfactory.data.retrofit.events;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceMetrics {
    @SerializedName("Metrics")
    private List<Metric> metrics;
    @SerializedName("Data")
    private List<Data> data;

    public DeviceMetrics(){}

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
