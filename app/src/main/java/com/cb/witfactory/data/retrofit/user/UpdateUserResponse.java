package com.cb.witfactory.data.retrofit.user;

import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse {

    @SerializedName("Key")
    private keyUserResponse Key;

    @SerializedName("address")
    private String address;

    @SerializedName("City")
    private String city;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;

    @SerializedName("telephone")
    private String telephone;

    @SerializedName("zip_code")
    private String zip_code;

    @SerializedName("Country")
    private String country;

    @SerializedName("suite")
    private String suite;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("appos")
    private String appos;


    public UpdateUserResponse() {
        this.address = address;
        this.city = city;
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
        this.zip_code = zip_code;
        this.country = country;
        this.suite = suite;
        this.device_id = device_id;
        this.appos = appos;
    }

    public keyUserResponse getKey() {
        return Key;
    }

    public void setKey(keyUserResponse key) {
        Key = key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getAppos() {
        return appos;
    }

    public void setAppos(String appos) {
        this.appos = appos;
    }
}
