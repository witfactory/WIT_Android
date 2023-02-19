package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

public class PayloadResponse {

    @SerializedName("temp")
    private Double temp;

    @SerializedName("hum")
    private Double hum;


    @SerializedName("pres")
    private Double pres;

    @SerializedName("co")
    private Double co;

    @SerializedName("co2")
    private Double co2;

    @SerializedName("no2")
    private Double no2;

    @SerializedName("c2h5oh")
    private Double c2h5oh;

    @SerializedName("h2")
    private Double h2;


    @SerializedName("nh3")
    private Double nh3;

    @SerializedName("ch4")
    private Double ch4;

    @SerializedName("c3h8")
    private Double c3h8;

    @SerializedName("c4h10")
    private Double c4h10;


    @SerializedName("iaq")
    private Integer iaq;

    @SerializedName("voc")
    private Double voc;

    @SerializedName("db_counter")
    private Integer db_counter;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("device_id")
    private String device_id;


    public PayloadResponse(Double temp, Double hum, Double pres, Double co, Double co2, Double no2, Double c2h5oh, Double h2, Double nh3, Double ch4, Double c3h8, Double c4h10, Integer iaq, Double voc, Integer db_counter, String user_name, String device_id) {
        this.temp = temp;
        this.hum = hum;
        this.pres = pres;
        this.co = co;
        this.co2 = co2;
        this.no2 = no2;
        this.c2h5oh = c2h5oh;
        this.h2 = h2;
        this.nh3 = nh3;
        this.ch4 = ch4;
        this.c3h8 = c3h8;
        this.c4h10 = c4h10;
        this.iaq = iaq;
        this.voc = voc;
        this.db_counter = db_counter;
        this.user_name = user_name;
        this.device_id = device_id;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHum() {
        return hum;
    }

    public void setHum(Double hum) {
        this.hum = hum;
    }

    public Double getPres() {
        return pres;
    }

    public void setPres(Double pres) {
        this.pres = pres;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getC2h5oh() {
        return c2h5oh;
    }

    public void setC2h5oh(Double c2h5oh) {
        this.c2h5oh = c2h5oh;
    }

    public Double getH2() {
        return h2;
    }

    public void setH2(Double h2) {
        this.h2 = h2;
    }

    public Double getNh3() {
        return nh3;
    }

    public void setNh3(Double nh3) {
        this.nh3 = nh3;
    }

    public Double getCh4() {
        return ch4;
    }

    public void setCh4(Double ch4) {
        this.ch4 = ch4;
    }

    public Double getC3h8() {
        return c3h8;
    }

    public void setC3h8(Double c3h8) {
        this.c3h8 = c3h8;
    }

    public Double getC4h10() {
        return c4h10;
    }

    public void setC4h10(Double c4h10) {
        this.c4h10 = c4h10;
    }

    public Integer getIaq() {
        return iaq;
    }

    public void setIaq(Integer iaq) {
        this.iaq = iaq;
    }

    public Double getVoc() {
        return voc;
    }

    public void setVoc(Double voc) {
        this.voc = voc;
    }

    public Integer getDb_counter() {
        return db_counter;
    }

    public void setDb_counter(Integer db_counter) {
        this.db_counter = db_counter;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
