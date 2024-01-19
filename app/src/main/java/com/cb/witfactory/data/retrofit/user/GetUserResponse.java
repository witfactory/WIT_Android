package com.cb.witfactory.data.retrofit.user;

import com.google.gson.annotations.SerializedName;

public class GetUserResponse {

    @SerializedName("City")
    private String city;

    @SerializedName("Country")
    private String country;


    @SerializedName("zip_code")
    private String zip_code;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("address")
    private String address;

    @SerializedName("email")
    private String email;

    @SerializedName("suite")
    private String suite;

    @SerializedName("User")
    private String user;

    @SerializedName("user_principal")
    private String user_principal;

    @SerializedName("last_name")
    private String last_name;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("telephone")
    private String telephone;

    @SerializedName("account_type")
    private String account_type;

    @SerializedName("id")
    private String id;

    public GetUserResponse(String city, String country, String zip_code, String device_id, String address, String email, String suite, String user, String user_principal, String telephone, String account_type, String id, String first_name, String last_name) {
        this.city = city;
        this.country = country;
        this.zip_code = zip_code;
        this.device_id = device_id;
        this.address = address;
        this.email = email;
        this.suite = suite;
        this.user = user;
        this.user_principal = user_principal;
        this.telephone = telephone;
        this.account_type = account_type;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser_principal() {
        return user_principal;
    }

    public void setUser_principal(String user_principal) {
        this.user_principal = user_principal;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getLastName() {
        return last_name;
    }
}
