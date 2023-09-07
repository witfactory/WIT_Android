package com.cb.witfactory.model;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static SharedPreferences mSharedPref;


    public PreferencesHelper(Context context) {
        mSharedPref = context.getSharedPreferences("witPreferences", Context.MODE_PRIVATE);
    }


    public static String getEmail(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setEmail(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static String getPassword(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setPassword(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static String getFirstName(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setFirstName(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static String getUser(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setUser(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static String getLastName(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setLastName(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static String getCountry(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setCountry(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static String getCity(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setCity(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static String getZipCode(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setZipCode(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static String getAddress(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setAddress(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static String getAccountType(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setAccountType(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static String getTelephone(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setTelephone(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public static String getUserPrincipal(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setUserPrincipal(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static String getPin(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void setPin(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }


    public static Boolean getTerminos(String key, Boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void setTerminos(String key, Boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }


    public static Boolean getPolitica(String key, Boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void setPolitica(String key, Boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    public static void clearUserPreference(){
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString("user", "");
        prefsEditor.putString("email", "");
        prefsEditor.putString("password", "");
        prefsEditor.putString("first_name", "");
        prefsEditor.putString("country", "");
        prefsEditor.putString("last_name", "");
        prefsEditor.putString("city", "");
        prefsEditor.putString("address", "");
        prefsEditor.putString("account_type", "");
        prefsEditor.putString("zip_code", "");
        prefsEditor.putString("telephone", "");
        prefsEditor.putString("txtPin", "");
        prefsEditor.putBoolean("politica", false);
        prefsEditor.putBoolean("terminos", false);
        prefsEditor.apply();
    }
}
