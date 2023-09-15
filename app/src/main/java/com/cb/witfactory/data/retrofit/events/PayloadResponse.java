package com.cb.witfactory.data.retrofit.events;

import com.google.gson.annotations.SerializedName;

public class PayloadResponse {

    @SerializedName("TEMP")
    private Double temp;

    @SerializedName("PRES")
    private Double pres;


    @SerializedName("CO2")
    private Double co2;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("led")
    private Boolean led;

    @SerializedName("VOC")
    private Double voc;

    @SerializedName("C4H10")
    private Double c4h10;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("C2H5OH")
    private Double c2h5oh;

    @SerializedName("H2")
    private Double h2;

   @SerializedName("C3H8")
    private Double c3h8;

    @SerializedName("CO")
    private Double co;

    @SerializedName("CH4")
    private Double ch4;

    @SerializedName("NO2")
    private Double no2;

    @SerializedName("db_counter")
    private String db_counter;

    @SerializedName("NH3")
    private Double nh3;

    @SerializedName("IAQ")
    private Double iaq;

    @SerializedName("id")
    private String id;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("HUM")
    private Double hum;


    public PayloadResponse() { }

    public PayloadResponse(Double temp, Double pres, Double co2, String timestamp, Boolean led, Double voc, Double c4h10, String device_id, Double c2h5oh, Double h2, Double c3h8, Double co, Double ch4, Double no2, String db_counter, Double nh3, Double iaq, String id, String user_name, Double hum) {
        this.temp = temp;
        this.pres = pres;
        this.co2 = co2;
        this.timestamp = timestamp;
        this.led = led;
        this.voc = voc;
        this.c4h10 = c4h10;
        this.device_id = device_id;
        this.c2h5oh = c2h5oh;
        this.h2 = h2;
        this.c3h8 = c3h8;
        this.co = co;
        this.ch4 = ch4;
        this.no2 = no2;
        this.db_counter = db_counter;
        this.nh3 = nh3;
        this.iaq = iaq;
        this.id = id;
        this.user_name = user_name;
        this.hum = hum;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPres() {
        return pres;
    }

    public void setPres(Double pres) {
        this.pres = pres;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getLed() {
        return led;
    }

    public void setLed(Boolean led) {
        this.led = led;
    }

    public Double getVoc() {
        return voc;
    }

    public void setVoc(Double voc) {
        this.voc = voc;
    }

    public Double getC4h10() {
        return c4h10;
    }

    public void setC4h10(Double c4h10) {
        this.c4h10 = c4h10;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
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

    public Double getC3h8() {
        return c3h8;
    }

    public void setC3h8(Double c3h8) {
        this.c3h8 = c3h8;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getCh4() {
        return ch4;
    }

    public void setCh4(Double ch4) {
        this.ch4 = ch4;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public String getDb_counter() {
        return db_counter;
    }

    public void setDb_counter(String db_counter) {
        this.db_counter = db_counter;
    }

    public Double getNh3() {
        return nh3;
    }

    public void setNh3(Double nh3) {
        this.nh3 = nh3;
    }

    public Double getIaq() {
        return iaq;
    }

    public void setIaq(Double iaq) {
        this.iaq = iaq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Double getHum() {
        return hum;
    }

    public void setHum(Double hum) {
        this.hum = hum;
    }
}
