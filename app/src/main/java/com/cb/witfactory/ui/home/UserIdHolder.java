package com.cb.witfactory.ui.home;

public class UserIdHolder {
    private static UserIdHolder instance;
    private String userId;

    private UserIdHolder() {
        // Constructor privado para evitar instanciación directa
    }

    public static synchronized UserIdHolder getInstance() {
        if (instance == null) {
            instance = new UserIdHolder();
        }
        return instance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
