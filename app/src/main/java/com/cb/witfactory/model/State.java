package com.cb.witfactory.model;

public class State {
    Integer id;
    Integer idCountry;
    String name;

    public State() {}

    public State(Integer id, Integer idCountry, String name) {
        this.id = id;
        this.idCountry = idCountry;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
