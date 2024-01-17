package com.cb.witfactory.model;

import java.util.List;

public class CountryResponse {
    private boolean error;
    private String msg;
    private List<CountryCapitals> data;

    // getters y setters
    public List<CountryCapitals> getData() {
        return data;
    }

    public void setData(List<CountryCapitals> data) {
        this.data = data;
    }
}
