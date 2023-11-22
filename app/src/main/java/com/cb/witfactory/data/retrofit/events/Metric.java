package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

public class  Metric {
    @SerializedName("title")
    private String title;
    @SerializedName("value")
    private double value;
    @SerializedName("color")
    private String color;

    public Metric(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}