package com.cb.witfactory.model;

public class City {
    Integer id;
    Integer idState;
    String name;

    public City() {}

    public City(Integer id, Integer idState, String name) {
        this.id = id;
        this.idState = idState;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
